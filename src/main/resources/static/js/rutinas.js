document.addEventListener("DOMContentLoaded", function () {
    const productoSelect = document.getElementById("productoSelect");
    const precioInput = document.getElementById("precioInput");

    
    if (!productoSelect || !precioInput) return;

    productoSelect.addEventListener("change", function () {
        const selectedOption = productoSelect.options[productoSelect.selectedIndex];
        const precio = selectedOption.getAttribute("data-precio");

        precioInput.value = precio || "";
    });
});

