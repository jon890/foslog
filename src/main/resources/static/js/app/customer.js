const customer = {
    backButton: null,

    init: function () {
        this.backButton = document.querySelector(".backButton");
        this.setEventListener();
    },

    setEventListener() {
        this.backButton.addEventListener("click", event => history.back());
    }

}

customer.init();