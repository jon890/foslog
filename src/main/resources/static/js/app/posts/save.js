var posts_save = {
    posts_data_form:null,

    init: function () {
        this.posts_data_form = document.querySelector("form[name='posts-save-form']");

        this.addEvents();
    },

    addEvents: function () {
        var _this = this;

        var save_button = document.querySelector(".save-button");
        save_button.addEventListener("click", function() {
            _this.posts_data_form.submit();
        });
    }
}

posts_save.init();