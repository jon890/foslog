var posts = {
    search_form: null,

    init: function() {
        this.search_form = document.querySelector("form[name='search-form']");

        this.addEvents();
    },

    addEvents: function() {
        var _this = this;

        var insert_button = document.querySelector(".posts-insert-button");
        insert_button.addEventListener("click", function () {
            _this.search_form.action = "/posts/save";
            _this.search_form.submit();
        });
    }
};

posts.init();