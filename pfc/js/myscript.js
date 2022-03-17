/**
 * Función que ordena cuanquier tabla por cada cabecera
 * de manera ascendente o descendente.
 */
document.querySelectorAll('th.order').forEach(th => {
    let ascendente = true;
    const spanElem = document.createElement('span');
    
    spanElem.style = "font-size:0.8rem; margin-left:0.5rem";
    spanElem.innerHTML = "▼";
    
    th.appendChild(spanElem);
    
    th.classList.add('order-inactive');

    const indice = Array.from(th.parentNode.children).indexOf(th);
    
    th.addEventListener('click', (e) => {
        document.querySelectorAll('th.order').forEach(elemento => {
            elemento.classList.remove('order-active');
            elemento.classList.add('order-inactive');
        });
        
        th.classList.remove('order-inactive');
        th.classList.add('order-active');

        if (!ascendente) {
            th.querySelector('span').innerHTML = '▲';
        } else {
            th.querySelector('span').innerHTML = '▼';
        }
        
        const array = Array.from(th.closest("table").querySelectorAll('tbody tr'));
        array.sort((n, m) => {
            if ((/[0-9]{2}-[0-9]{2}-[0-9]{4}/gm).test(n.children[indice].innerText)){
                const valor_n = new Date(n.children[indice].innerText).getTime();
                const valor_m = new Date(m.children[indice].innerText).getTime();
                return (ascendente) ? (valor_n - valor_m) : (valor_m - valor_n);
            } else if (Number.isNaN(Number.parseFloat(n.children[indice].innerText))){
                const valor_n = n.children[indice].innerText;
                const valor_m = m.children[indice].innerText;
                return (ascendente) ? valor_n.localeCompare(valor_m) : valor_m.localeCompare(valor_n);
            } else {
                const valor_n = Number.parseFloat(n.children[indice].innerText);
                const valor_m = Number.parseFloat(m.children[indice].innerText);
                return (ascendente) ? (valor_n - valor_m) : (valor_m - valor_n);
            }
            
        });
        array.forEach(elemento => {
            th.closest("table").querySelector("tbody").appendChild(elemento)
        });
        ascendente = !ascendente;
    });
})

/**
 * Funcion para filtrar el contenido de las tablas 
 */
function filtroTablas(tablaObjetivo, textoRecibido) {
    var texto, filtro, tabla, tr, td, celda, i, j;
    texto = document.getElementById(textoRecibido);
    filtro = texto.value.toUpperCase();
    tabla = document.getElementById(tablaObjetivo);
    tr = tabla.getElementsByTagName("tr");
    for (i = 1; i < tr.length; i++) {
        tr[i].style.display = "none";
        td = tr[i].getElementsByTagName("td");
        for (var j = 0; j < td.length - 1; j++) {
            celda = tr[i].getElementsByTagName("td")[j];
            if (celda) {
                if (celda.innerHTML.toUpperCase().indexOf(filtro) > -1) {
                    tr[i].style.display = "";
                    break;
                }
            }
        }
    }
}

/**
 * Función para comprobar el DNI
 */
function dniValido(dni){
    if (/^\d{8}[a-zA-Z]$/.test(dni)) {
        var n = dni.substr(0,8);
        var c = dni.substr(8,1);
        return (c.toUpperCase() == 'TRWAGMYFPDXBNJZSQVHLCKET'.charAt(n%23));
    }
    return false;
}

/**
 * Comprobación del fassword igual en los dos campos 'on fly'
 */
function checkPassword() {

    pass1 = document.getElementById('activationpass1');
    pass2 = document.getElementById('activationpass2');
 
    if (pass1.value != pass2.value) {
        document.getElementById("error").classList.add("mostrar");
        document.getElementById("ok").classList.remove("mostrar");
        document.getElementById("btnEnviarActivation").disabled = true;
        return false;
    } else {
        document.getElementById("error").classList.remove("mostrar");
        document.getElementById("ok").classList.add("mostrar");
        document.getElementById("btnEnviarActivation").disabled = false;
        return true;
    }
 
}

/**
 * Cuenta atras para mandar a la página principal
 */
var segundos = 10;
function countdown() {
    segundos = segundos - 1;
    if (segundos < 0) {
        window.location = "http://flightembl.com";
    } else {
        document.getElementById("countdown").innerHTML = segundos;
        window.setTimeout("countdown()", 1000);
    }
}
countdown();

/**
 * Mostrar u ocultar la contraseña en la página de autenticación
 */
function passShowHide() {
    var pass = document.getElementById("txtpassword");
    var show = document.getElementById("show");
    var hide = document.getElementById("hide");
    hide.classList.remove("d-none");
    if (pass.type === "password") {
        pass.type = "text";
        show.style.display = "none";
        hide.style.display = "block";
    } else {
        pass.type = "password";
        show.style.display = "block";
        hide.style.display = "none";
    }
}