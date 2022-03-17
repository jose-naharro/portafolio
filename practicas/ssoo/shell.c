#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <dirent.h>
#include <sys/stat.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <sys/resource.h>

#define RUNNING 1 
#define DONE 2
#define STOPPED 3
#define KILLED 4
#define DELETED 5

struct DatosProceso{
   pid_t pid;
   char comando[256];
   time_t instante;
   int estado;
   int prioridad;
};

struct DatosProceso listaProcesos[256];
int tamanho_lista = 0;


void mostrarPrompt(){
   printf("-> ");
}

int TrocearCadena(char * cadena, char * trozos[]){ // devolve un int co numero de trozos (opciones)
   int i=1;

   if ((trozos[0]=strtok(cadena," \n\t"))==NULL) return 0;  // si a cabeza ven vacia (apunta anull) Ã© que ten 0 trozos
   while ((trozos[i]=strtok(NULL," \n\t"))!=NULL)            // mentras cadena sea distinto de null sigue collendo trozos
      i++;
   return i;   // Num de trozos que partiu (estan todos metidos en trozos[] )
}

void salirShell(){    
   exit(EXIT_SUCCESS);
}

void mostrarAutores(){

   printf("Nombre: Ivan del Rio Paderne         | login: ivan.paderne \n");
   printf("Nombre: Jose Carlos Naharro Gordillo | login: jose.naharro \n");
}


void obtenerPid(char *partes){ 

   pid_t pid;       

   if (partes == NULL){   // no se recibe ninguna especificacion
      pid=getpid();
      printf("El pid del proceso actual es: %d\n",pid);
   }else{
      if(strcmp(partes,"-p")==0){   // o 'partes' que lle estamos pasando e o partes[1] do main
         pid = getppid();
         printf("El pid del proceso padre es: %d\n",pid);
      }else
         perror("Error pid\n");
   }
}

void cd(char *partes){
   char *actual = (char*) malloc(1000 * sizeof(char*));  //(char)* estou parseando e decir sempre vai a ser punteiro a char
   
   if (partes==NULL){
      getcwd(actual,150);        //getcwd obten o directorio actual, actual Ã© un punteiro o directorio actual, 150 tamano
      printf("El directorio actual es: %s\n", actual);
   }else{   
      if (chdir(partes) == 0){  //aqui xa fixo o chdir
         getcwd(actual, 150);
         printf("Se cambio el directorio a: %s\n", actual);
      }else{   
         perror("Error cd");
      }
   }
   free(actual);
}

void delete(char *partes){       // para meterlle o path vai sin '/' o principio
   if (remove(partes)==0)
      printf("%s borrado correctamente \n", partes);
   else{
      printf("no se puede borrar %s\n", partes);
      perror("delete");
   }
}


void shortlist (DIR *directorio, int flaga){  //pasolle o punteiro porque despois xa o cerro eu con closedir
   
   struct dirent *direntp;
   
   if (flaga==0)
      while((direntp=readdir(directorio))!=NULL){     //mentras non acaba todo o que haxa no directorio
         if(direntp->d_name[0]!='.')
            printf("%s\n", direntp->d_name);
      }
   else
      while((direntp=readdir(directorio))!=NULL){     //mentras non acaba todo o que haxa no directorio
         printf("%s\n", direntp->d_name); 
      }    
   closedir(directorio);
}
                                                
char TipoFichero (mode_t m){

   switch (m&S_IFMT) { /*and bit a bit con los bits de formato,0170000 */
      case S_IFSOCK: return 's'; /*socket */
      case S_IFLNK:  return 'l';/*symbolic link*/
      case S_IFREG:  return '-';/* fichero normal*/
      case S_IFBLK:  return 'b';/*block device*/
      case S_IFDIR:  return 'd';/*directorio */
      case S_IFCHR:  return 'c';/*char device*/
      case S_IFIFO:  return 'p';/*pipe*/
      default: return '?';/*desconocido, no deberia aparecer*/
   }
}                    
   
char * ConvierteModo (mode_t m, char *permisos){

   strcpy (permisos,"---------- ");
   permisos[0]=TipoFichero(m);
   if (m&S_IRUSR) permisos[1]='r';/*propietario*/
   if (m&S_IWUSR) permisos[2]='w';
   if (m&S_IXUSR) permisos[3]='x';
   if (m&S_IRGRP) permisos[4]='r';/*grupo*/
   if (m&S_IWGRP) permisos[5]='w';
   if (m&S_IXGRP) permisos[6]='x';
   if (m&S_IROTH) permisos[7]='r';/*resto*/
   if (m&S_IWOTH) permisos[8]='w';
   if (m&S_IXOTH) permisos[9]='x';
   if (m&S_ISUID) permisos[3]='s';/*setuid, setgid y stickybit*/
   if (m&S_ISGID) permisos[6]='s';
   if (m&S_ISVTX) permisos[9]='t';
    
   return (permisos); 
}

// quedan alguns allocs sin liberar por culpa dos "uid" e dos "gid"

char *nombre (uid_t uid){
   struct passwd *pwd;
   if((pwd=getpwuid(uid))==NULL)
      return ("user?");
   else
      return (pwd->pw_name);
}

char *grupo (gid_t gid){
   struct group *grp;
   if((grp = getgrgid(gid)) == NULL)
        return ("????");

   return grp->gr_name;
}


void imprimirDirectorio(char *dir,struct dirent *direntp){
   char auxiliar[1024];
   struct stat s;
   char  permisos[15];
   struct tm *tlocal;

   sprintf(auxiliar, "%s/%s", dir, direntp->d_name);  //estou creando en auxiliar unha nova direccion para poder entrar nesa direccion
   if (lstat(auxiliar, &s)==-1){                //meto en &s o lstat de auxiliar
      perror("Error lstat");
      return;
   }
   printf("%li ",s.st_ino);
   printf ("%s ",ConvierteModo(s.st_mode,permisos));
   printf("%u ",s.st_nlink);
   printf ("%s %s ", nombre(s.st_uid), grupo(s.st_gid));
   printf("%li ",s.st_size);
   tlocal=(localtime(&(s.st_mtime)));
   printf("%d-%d-%d %d:%d ",(tlocal->tm_year+1900),tlocal->tm_mon,tlocal->tm_mday,tlocal->tm_hour,tlocal->tm_min);
   printf("%s\n",direntp->d_name);      
}

void list(char *dir, int flaga, int flags){//recibimos ls -li os argumentos o list vai sin barra
   DIR *directorio;
   struct dirent *direntp;    //un rexistro que recorre todo o directorio e sabe tipos de archivos etc (mirar no manual)
   
   directorio = opendir(dir); //con DIR creo un punteiro que apunta a primeira posicion da direccion "actual"
   if (directorio==NULL)       
      perror("Error list");
   else if (flags==1)
         shortlist(directorio, flaga);
   else{
      while((direntp=readdir(directorio))!=NULL){
         if (flaga==1)
            imprimirDirectorio(dir,direntp);
         else if (direntp->d_name[0]!='.')
            imprimirDirectorio(dir,direntp);
      }      
   }
}


void menu_list(char **partes){

   int cont = 1;  //empezo en 1 porque Ã© a parte que me importa
   int flaga = 0;
   int flags = 0;
   char *dir="."; //directorio por defecto -> poden pasar o directorio de ultimo
   
   while(partes[cont]!=NULL){
      if (strcmp(partes[cont],"-s")==0)
         flags=1;
      else if (strcmp(partes[cont],"-a")==0)   //activacion de flags segun entrada
         flaga=1;
      else
         dir=partes[cont];

      cont++;           //cando comproba todo e chega a null
   }
   list(dir, flaga, flags);   //flags e dir recibidos
}
   
   
void hacerFork(){
   pid_t hijo = fork();
   if (hijo==-1)
      perror("Imposible crear hijo");
   else if (hijo==0)
      printf("Soy el hijo de %d mi pid es %d\n", getppid(), getpid());
   else if (hijo !=0)
      waitpid (hijo, NULL, 0);
}


void priority (char **partes){   // porque lle vou pasar mais de un argumento
   int which = PRIO_PROCESS;     //esto mireino no manual
   
   if (partes[1]!=NULL) {
      pid_t pid = atoi(partes[1]);
      if (partes[2] != NULL){
         if(setpriority(which, pid, atoi(partes[2]))==-1) //which dinos si o proceso e indidual ou en grupo / pid do proceso / atoi convirte
             perror("Error setpriority");                 //cadena de char a enteiro para que o reconoza a funcion setpriority  
      }else
         printf ("La prioridad del proceso es %d\n", getpriority(PRIO_PROCESS,pid));
   }else
      printf("la prioridad del proceso es %d\n", getpriority(PRIO_PROCESS, 0));
         
   // funciona facendo un xterm e un top e mirando priority 1322 10 no shell           
}                 

void estadosProcesos(struct DatosProceso *elemento){
   int estado;

   if (elemento->pid == waitpid(elemento -> pid, &estado, WNOHANG | WUNTRACED | WCONTINUED)) {
      if (WIFCONTINUED(estado)){
         elemento->estado = 1; 
      }else if (WIFEXITED(estado)) {
         elemento->estado = 2; 
      }else if (WIFSTOPPED(estado)){
         elemento->estado = 3; 
      }else if (WIFSIGNALED(estado)) {
         elemento->estado = 4; 
      }   
   }
}


char *estadoTOstring(int estado){
    
   if (estado == RUNNING) return "Running";
   else if (estado == DONE) return "Done";
   else if (estado == STOPPED) return "Stopped";
   else if (estado == KILLED) return "Killed";
   else return "NULL";
}

void imprimirProcesos(struct DatosProceso elemento){
   printf("%5d%5i%15s%10s%30s", elemento.pid,elemento.prioridad,elemento.comando,estadoTOstring(elemento.estado),ctime(&elemento.instante));
}

int jobs_pid(char *partes){
   int i;
   int EsJobs_pid = 0;
   char pid[25];
   
   for(i=0; i < tamanho_lista; i++){
      sprintf(pid, "%d", listaProcesos[i].pid);
      if ((partes != NULL) && (!strcmp (partes,pid))){
         imprimirProcesos(listaProcesos[i]);
         EsJobs_pid = 1;
      }
   } 
   return EsJobs_pid;
}


void jobs(char *partes){
   int mostrar, i;
   
   if (jobs_pid(partes) == 0){ // para ejercicio jobs_pid
      if (partes!=NULL){
         if(!strcmp (partes,"all")) mostrar = 0;
         if(!strcmp (partes,"term"))  mostrar = DONE;
         else if (!strcmp (partes,"sig")) mostrar = KILLED;
         else if (!strcmp (partes,"stop")) mostrar = STOPPED;
         else if (!strcmp (partes,"act")) mostrar = RUNNING;
         else mostrar = 0;        
      }
      for(i=0; i < tamanho_lista; i++){
         estadosProcesos(&listaProcesos[i]);
         if (partes != NULL){ 
            if(mostrar == listaProcesos[i].estado) imprimirProcesos(listaProcesos[i]);
            else  if((mostrar == 0)&&(listaProcesos[i].estado != DELETED)) {
               imprimirProcesos(listaProcesos[i]);
            }
         }else if (listaProcesos[i].estado != DELETED){ 
            imprimirProcesos(listaProcesos[i]);      
         }
      }
   }  
}

void clearjobs(){
   int i;
   int eliminados_normal = DONE;
   int eliminados_senhal = KILLED;

   for(i=0; i<tamanho_lista; i++){
      estadosProcesos(&listaProcesos[i]);   
      if((eliminados_normal == listaProcesos[i].estado) || (eliminados_senhal == listaProcesos[i].estado))
         listaProcesos[i].estado = DELETED;
      if (listaProcesos[i].estado != DELETED) {
         imprimirProcesos(listaProcesos[i]);
      }   
   }         
}


void exec(char **partes){

   int r;
   
   if(partes[1] == NULL)
      perror("Faltan argumentos");
   else{
      r = execvp(partes[1], partes+1);
      if (r == -1)
         perror("Fallo en execute");
   } 
}

int exec2(char **partes){

   int r = 0;
   
   if(partes[1] == NULL)
      perror("Faltan argumentos");
   else{
      r = execvp(partes[1], partes+1);
      if (r == -1)
         perror("Fallo en execute");
   } 

   return r;
}

void exec3(char **partes){

   int r;
   
   if(partes[0] == NULL)
      perror("Faltan argumentos");
   else{
      r = execvp(partes[0], partes);
      if (r == -1)
         perror("Fallo en execute");
   } 
}


void meterListaSP(char **partes, pid_t pid){
   int i;
   struct DatosProceso elemento; 
   time_t instante = time(NULL);
   
   elemento.pid = pid;
   strcpy(elemento.comando,partes[0]);
   for (i=1;partes[i]!=NULL;i++){
      strcat(elemento.comando," ");
      strcat(elemento.comando,partes[i]);
   }
   elemento.prioridad = getpriority(PRIO_PROCESS,pid);
   elemento.instante = instante;
   elemento.estado = RUNNING;   
   listaProcesos[tamanho_lista] = elemento;
   tamanho_lista ++;
}

void ejecucion (char **partes){
  pid_t pid;
  int splano;

  splano = 0;
  if ((pid=fork()) == 0) {
      exec3(partes); 
      exit(127);
   }else if (!splano)
         waitpid(pid,NULL,0);
   else meterListaSP(partes,pid);
}

void ejecucion_pp (char **partes){
   pid_t pid;
   int splano;

   splano = 0;
   if ((pid=fork()) == 0) {
      exec2(partes); 
      exit(127);
   }else if (!splano)
         waitpid(pid,NULL,0);
   else meterListaSP(partes,pid);
} 

void ejecucion_sp (char **partes){
   pid_t pid;
   int splano;
   
   splano = 1;
   if ((pid=fork()) == 0) {
      exec2(partes);
      exit(127);
   }else if (!splano)
      waitpid(pid,NULL,0);
    else meterListaSP(partes,pid);
} 


int esdirectorio(char *dir, char *f){

    char auxiliar[1024];
    
    struct stat s;
    sprintf (auxiliar, "%s/%s", dir,f);
    if ((lstat(auxiliar,&s)!=-1)&&(S_ISDIR(s.st_mode)))      //si se pode crear struct stat de aux -> true
        return 1;
    return 0;
}


void deltree(char *directoriom){

   DIR *directorio;
   struct dirent *direntp;
   int i;
   int c=0;                     //vouno usar de contador para os path
   char esborrado[1024];
   char rec[1024];
   char *name[1024];
   
   if ((directorio=opendir(directoriom))==NULL)     // comprobar open
        perror("Error opendir\n");
   else{
      while((direntp=readdir(directorio))!=NULL){
          if (esdirectorio(directoriom, direntp->d_name)){
             if ((strcmp(direntp->d_name,".")!=0) && (strcmp(direntp->d_name,"..")!=0))
                name[c++]=direntp->d_name;
          }else{                                  //file
             strcpy(esborrado,directoriom);
             sprintf(esborrado,"%s/%s",directoriom,direntp->d_name);
             if(remove(esborrado)!=0)
                perror("Error remove\n");
         }
      }  
      for(i=0;i<c;i++){                                       
         strcpy(rec,directoriom);            
         sprintf(rec,"%s/%s",directoriom,name[i]);             
         deltree(rec);
      }  
   }
   closedir(directorio);
   rmdir(directoriom);
}

void procesarEntrada(char **partes){

   if ((strcmp(partes[0],"quit")==0) || (strcmp(partes[0],"fin")==0) || (strcmp(partes[0],"exit")==0)) salirShell();
   else if(strcmp(partes[0],"autores")==0) mostrarAutores();
   else if(strcmp(partes[0], "pid")==0) obtenerPid(partes[1]);
   else if(strcmp(partes[0], "cd")==0) cd(partes[1]);
   else if(strcmp(partes[0], "delete")==0) delete(partes[1]);
   else if(strcmp(partes[0], "deltree")==0) deltree(partes[1]);
   else if(strcmp(partes[0], "list")==0) menu_list(partes);
   else if(strcmp(partes[0], "fork")==0) hacerFork(partes[1]);
   else if(strcmp(partes[0], "priority")==0) priority(partes);
   else if(strcmp(partes[0], "exec")==0) exec(partes);
   else if(strcmp(partes[0], "pplano")==0) ejecucion_pp(partes);
   else if(strcmp(partes[0], "splano")==0) ejecucion_sp(partes);
   else if(strcmp(partes[0], "jobs")==0) jobs(partes[1]);
   else if(strcmp(partes[0], "clearjobs")==0) clearjobs();
   else ejecucion(partes); 
}


void main(){

   int salir = 0;
   char entrada[120];
   char *cadena;     // cadena es un puntero a tipo char
   char **partes;    // partes es un puntero a otro puntero tipo char
   int i;            // enteiro que nos vai decir cantas partes ten o que troceamos

   do{
      partes = (char**) malloc (1000*sizeof(char*));
      mostrarPrompt();
      gets(entrada);
      cadena = entrada;
      i = TrocearCadena(cadena, partes);  //meto en i o numero de partes pa ir acedendo e comparando a gas
                                          //o puntero cadena apunta Ã³ que vamos a trocear
      if(partes[0] == NULL) continue;// Si lle dou a enter a movida da NULL e sije

      procesarEntrada(partes);
 
      free(partes);

   }while (salir == 0);

}
