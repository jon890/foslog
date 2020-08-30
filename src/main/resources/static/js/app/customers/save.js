const customerSave = {
    backButton: null,
    submitButton: null,
    BASE_URL: "/snack-in-the-garden/customer",

    init() {
        this.backButton = document.querySelector(".backButton");
        this.submitButton = document.querySelector(".submitButton");

        this.setEventListener();
    },

    setEventListener() {
        if (this.backButton) {
            this.backButton.addEventListener("click", () => {
                location.href = `${this.BASE_URL}/list`;
            });
        }

        if (this.submitButton) {
            this.submitButton.addEventListener("click", async (_) => {
                const response = await this.saveCustomer();

                console.log("BiFoS : 회원 정보 저장");
                console.log(response);

                if (response) {
                    alert("성공적으로 저장 되었습니다!");
                    location.href = `${this.BASE_URL}/list`;
                } else {
                    alert("오류가 발생했습니다.\n계속해서 발생하면 관리자에게 문의하세요\njon89071@gmail.com");
                }
            });
        }
    },

    async saveCustomer() {
        const customerForm = document.querySelector("form[name='customer-form']");
        const selectedDates = fosCalendar.getSelectedDates();

        const data = {
            "name": customerForm.name.value,
            "address": customerForm.address.value,
            "phoneNumber": customerForm.phoneNumber.value,
            "memo": customerForm.memo.value,
            "selectedDates": selectedDates
        };

        return await ApiUtils.request(`/api/v1/customers`, data);
    },
}

customerSave.init();
