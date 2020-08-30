const customerList = {
    init() {
        this.setEventListener()
    },

    setEventListener() {
        // 리스트 아이템 클릭 이벤트
        const listItems = document.querySelectorAll(".customers")

        if (listItems.length > 0) {
            Array.from(customerList)
                .forEach(domObject => domObject.addEventListener("click", this.moveCustomerDetail));
        }
    },

    moveCustomerDetail() {
        console.log("BiFoS : moveCustomerDetail");
        console.log(this);

        const id = this.dataset.id;
        location.href = `/snack-in-the-garden/customer/detail?customerId=${id}`;
    }
}

customerList.init();