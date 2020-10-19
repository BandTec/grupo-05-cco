function plotarParques() {

    var autoUpdate = false,
        timeTrans = 4000;

    var cdSlider = document.querySelector('.cd-slider'),
        item = cdSlider.querySelectorAll("li"),
        nav = cdSlider.querySelector("nav");

    item[0].className = "current_slide";



    // Detect IE
    // hide ripple effect on IE9
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE");
    if (msie > 0) {
        var version = parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
        if (version === 9) {
            cdSlider.className = "cd-slider ie9";
        }
    }

    if (item.length <= 1) {
        nav.style.display = "none";
    }

    function prevSlide() {
        var currentSlide = cdSlider.querySelector("li.current_slide"),
            prevElement = currentSlide.previousElementSibling,
            prevSlide = (prevElement !== null) ? prevElement : item[item.length - 1],

            el = document.createElement('span');

        currentSlide.className = "";
        prevSlide.className = "current_slide";

        nav.children[0].appendChild(el);





    }

    function nextSlide() {
        var currentSlide = cdSlider.querySelector("li.current_slide"),
            nextElement = currentSlide.nextElementSibling,
            nextSlide = (nextElement !== null) ? nextElement : item[0],

            el = document.createElement('span');

        currentSlide.className = "";
        nextSlide.className = "current_slide";

        nav.children[1].appendChild(el);



    }





    nav.querySelector(".next").addEventListener('click', function (event) {
        event.preventDefault();
        nextSlide();

    });

    nav.querySelector(".prev").addEventListener("click", function (event) {
        event.preventDefault();
        prevSlide();

    });

    //autoUpdate
    setInterval(function () {
        if (autoUpdate) {
            nextSlide();

        };
    }, timeTrans);

}