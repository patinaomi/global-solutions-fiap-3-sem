// Please see documentation at https://learn.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.


document.addEventListener("DOMContentLoaded", function () {
    const textElement = document.querySelector('.progress-text');
    textElement.addEventListener('animationstart', () => {
        textElement.classList.add('animate-text');
    });

    textElement.addEventListener('animationend', () => {
        textElement.classList.remove('animate-text');
    });
});


