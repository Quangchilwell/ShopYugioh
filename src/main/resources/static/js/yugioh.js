const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

var cart = $(".header-cart-link")
var cartProduct = $(".cart-product")
var yugiWeb = $('.yugioh-web')

const yugiohWeb = {
    handleEvent : function()
    {
        var _this = this
        cart.onclick = function() {
            cartProduct.classList.toggle("open");
        }
    },

    start : function()
    {
        this.handleEvent()
    }
}

yugiohWeb.start()

