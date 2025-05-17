document.addEventListener('DOMContentLoaded', function () {
    var swiper1 = new Swiper(".mySwiper-1", {
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });

    var swiper2 = new Swiper(".mySwiper-2", {
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });

    // Función para ajustar la posición de los botones de navegación
    function adjustNavButtons(swiper) {
        var wrapperWidth = swiper.$el.width(); // Obtiene el ancho del contenedor del Swiper
        var nextButton = swiper.navigation.nextEl;
        var prevButton = swiper.navigation.prevEl;

        nextButton.style.right = "50px"; // Ajusta la distancia desde el borde derecho
        nextButton.style.top = "50%"; // Ajusta la posición verticalmente en el centro

        prevButton.style.left = "50px"; // Ajusta la distancia desde el borde izquierdo
        prevButton.style.top = "50%"; // Ajusta la posición verticalmente en el centro
    }

    adjustNavButtons(swiper1); // Ajusta los botones de navegación del primer Swiper
    adjustNavButtons(swiper2); // Ajusta los botones de navegación del segundo Swiper
});

//carrito


document.addEventListener('DOMContentLoaded', function () {
    var carrito = document.getElementById('carrito');
    var listaCarrito = document.querySelector('#lista-carrito tbody');
    var vaciarCarritoBtn = document.getElementById('vaciar-carrito');

    // Función para agregar un producto al carrito
    function agregarProductoAlCarrito(event) {
        event.preventDefault();
        if (event.target.classList.contains('agregar-carrito')) {
            var producto = event.target.parentElement.parentElement;
            obtenerInfoProducto(producto);
        }
    }

    // Función para obtener la información de un producto
    function obtenerInfoProducto(producto) {
        var productoInfo = {
            imagen: producto.querySelector('img').src,
            nombre: producto.querySelector('h3').textContent,
            precio: producto.querySelector('.precio').textContent,
            id: producto.querySelector('.agregar-carrito').getAttribute('data-id')
        };
        agregarProductoAlHTML(productoInfo);
    }

    // Función para agregar un producto al HTML del carrito
    function agregarProductoAlHTML(producto) {
        var row = document.createElement('tr');
        row.innerHTML = `
            <td><img src="${producto.imagen}" width="100"></td>
            <td>${producto.nombre}</td>
            <td>${producto.precio}</td>
            <td><a href="#" class="borrar-producto" data-id="${producto.id}">X</a></td>
        `;
        listaCarrito.appendChild(row);
    }

    // Función para eliminar un producto del carrito
    function eliminarProducto(event) {
        event.preventDefault();
        if (event.target.classList.contains('borrar-producto')) {
            var productoId = event.target.getAttribute('data-id');
            var producto = event.target.parentElement.parentElement;
            producto.remove();
        }
    }

    // Evento para agregar un producto al carrito
    document.body.addEventListener('click', agregarProductoAlCarrito);

    // Evento para eliminar un producto del carrito
    listaCarrito.addEventListener('click', eliminarProducto);

    // Evento para vaciar el carrito
    vaciarCarritoBtn.addEventListener('click', vaciarCarrito);
    
    // Función para vaciar el carrito
    function vaciarCarrito() {
        while (listaCarrito.firstChild) {
            listaCarrito.removeChild(listaCarrito.firstChild);
        }
    }
});

document.addEventListener('DOMContentLoaded', function() {
    var enlaces = document.querySelectorAll('.btn-1'); // Agrega un punto antes de 'btn-1'
  
    enlaces.forEach(function(enlace) {
      enlace.addEventListener('click', function(event) {
        event.preventDefault(); // Evita que el enlace realice su acción por defecto
        window.location.href = 'historia.html'; // Reemplaza 'URL_del_enlace' con la dirección URL a la que deseas redirigir
      });
    });
});



// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene el elemento de la galería
    var galeriaLink = document.querySelector("a[href='galeria.html']");

    // Agrega un evento de clic al enlace de la galería
    galeriaLink.addEventListener("click", function(event) {
        // Previene el comportamiento predeterminado del enlace
        event.preventDefault();

        // Redirige a la página de la galería
        window.location.href = "galeria.html";
    });
});

// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene el elemento del enlace a la página de contacto
    var contactoLink = document.querySelector("a[href='@{/login}']");

    // Agrega un evento de clic al enlace de contacto
    contactoLink.addEventListener("click", function(event) {
        // Previene el comportamiento predeterminado del enlace
        event.preventDefault();

        // Redirige a la página de contacto
        window.location.href = '@{/login}';
    });
});

// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene el elemento del enlace a la página de contacto
    var contactoLink = document.querySelector("a[href='@{/}']");

    // Agrega un evento de clic al enlace de contacto
    contactoLink.addEventListener("click", function(event) {
        // Previene el comportamiento predeterminado del enlace
        event.preventDefault();

        // Redirige a la página de contacto
        window.location.href = '@{/}';
    });
});




// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene el elemento del enlace a la página de historia
    var historiaLink = document.querySelector("a[href='historia.html']");

    // Agrega un evento de clic al enlace de historia
    historiaLink.addEventListener("click", function(event) {
        // Previene el comportamiento predeterminado del enlace
        event.preventDefault();

        // Redirige a la página de historia
        window.location.href = "historia.html";
    });
});

// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene todos los enlaces del footer
    var footerLinks = document.querySelectorAll(".footer a");

    // Recorre todos los enlaces del footer
    footerLinks.forEach(function(link) {
        // Agrega un evento de clic a cada enlace del footer
        link.addEventListener("click", function(event) {
            // Previene el comportamiento predeterminado del enlace
            event.preventDefault();

            // Obtiene la URL del enlace
            var url = link.getAttribute("href");

            // Redirige a la URL del enlace
            window.location.href = url;
        });
    });
});

// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene el elemento del enlace a la página de perfil
    var perfilLink = document.querySelector("a[href=\'/perfil\']");

    // Agrega un evento de clic al enlace de perfil
    perfilLink.addEventListener("click", function(event) {
        // Previene el comportamiento predeterminado del enlace
        event.preventDefault();

        // Redirige a la página de perfil
        window.location.href = "\'/perfil\'";
    });
});

// Espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    // Obtiene el elemento del enlace a la página de perfil
    var perfilLink = document.querySelector("a[href='@{/nosotros}']");

    // Agrega un evento de clic al enlace de perfil
    perfilLink.addEventListener("click", function(event) {
        // Previene el comportamiento predeterminado del enlace
        event.preventDefault();

        // Redirige a la página de perfil
        window.location.href = '@{/nosotros}';
    });
});










