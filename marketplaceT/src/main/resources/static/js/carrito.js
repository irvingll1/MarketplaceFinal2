const Clickbutton = document.querySelectorAll('.button')
const tbody = document.querySelector('.tbody')
let carrito = []

Clickbutton.forEach(btn => {
    btn.addEventListener('click', addToCarritoItem)
})


function addToCarritoItem(e) {
    const button = e.target
    const item = button.closest('.card')
    const itemid = item.querySelector('.ideee').textContent;
    const itemTitle = item.querySelector('.card-title').textContent;
    const itemPrice = item.querySelector('.precio').textContent;
    const itemCant = item.querySelector('.cantidad').value;
    const itemImg = item.querySelector('.card-img-top').src;

    const newItem = {
        id: itemid,
        title: itemTitle,
        precio: itemPrice,
        img: itemImg,
        cantidad: itemCant
    }

    addItemCarrito(newItem)
}
function addItemCarrito(newItem) {

    const alert = document.querySelector('.alert')

    setTimeout(function () {
        alert.classList.add('hide')
    }, 2000)
    alert.classList.remove('hide')

    const InputElemnto = tbody.getElementsByClassName('input__elemento')
    for (let i = 0; i < carrito.length; i++) {

        if (carrito[i].title.trim() === newItem.title.trim()) {
            carrito[i].cantidad++;
            const inputValue = InputElemnto[i]
            inputValue.value++;
            CarritoTotal()
            return null;
        }
    }

    carrito.push(newItem)

    renderCarrito()
}

function renderCarrito() {
    tbody.innerHTML = ''
    carrito.map(item => {
        const tr = document.createElement('tr')
        tr.classList.add('ItemCarrito')

        const Content = `
                            <th scope="row"  class="table__id"><p>${item.id}</p></th>
                            <td class="table__productos">
                              <img src=${item.img}  alt="">
                              <h6  class="title">${item.title}</h6>
                            </td>
                            <td class="table__price"><p>S/ ${item.precio}</p></td>
                            <td class="table__cantidad">
                              <input type="number" min="1" value=${item.cantidad} class="input__elemento">
                              <button class="delete btn btn-danger">x</button>
                            </td>`
        tr.innerHTML = Content;
        tbody.append(tr)

        tr.querySelector(".delete").addEventListener('click', removeItemCarrito)
        tr.querySelector(".input__elemento").addEventListener('change', sumaCantidad)
    })
    CarritoTotal()
}

function CarritoTotal() {
    let Total = 0;
    const itemCartTotal = document.querySelector('.itemCartTotal')
    const items = document.querySelector('.json')
    carrito.forEach((item) => {
        const precio = Number(item.precio.replace("s/.", ''))
        Total = Total + precio * item.cantidad
        Total = Math.round(Total * 100) / 100
    })

    itemCartTotal.innerHTML = `Total S/ ${Total}`
    items.value = `${JSON.stringify(carrito)}`
    addLocalStorage()
}

function removeItemCarrito(e) {
    const buttonDelete = e.target
    const tr = buttonDelete.closest(".ItemCarrito")
    const title = tr.querySelector('.title').textContent;
    for (let i = 0; i < carrito.length; i++) {

        if (carrito[i].title.trim() === title.trim()) {
            carrito.splice(i, 1)
        }
    }

    const alert = document.querySelector('.remove')

    setTimeout(function () {
        alert.classList.add('remove')
    }, 2000)
    alert.classList.remove('remove')
    tr.remove()
    CarritoTotal()
}

function sumaCantidad(e) {
    const sumaInput = e.target
    const tr = sumaInput.closest(".ItemCarrito")
    const title = tr.querySelector('.title').textContent;
    carrito.forEach(item => {
        if (item.title.trim() === title) {
            sumaInput.value < 1 ? (sumaInput.value = 1) : sumaInput.value;
            item.cantidad = sumaInput.value;
            CarritoTotal()
        }
    })
}

function addLocalStorage() {
    sessionStorage.setItem('carrito', JSON.stringify(carrito))

}

function deleteLocalStorage() {
    sessionStorage.removeItem('carrito')
}


window.onload = function () {
    const storage = JSON.parse(sessionStorage.getItem('carrito'));
    if (storage) {
        carrito = storage;
        renderCarrito()
    }

}


