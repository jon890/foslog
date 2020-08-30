const fosCalendar = {
    init() {
        const calendar = document.querySelector(".fos-calendar");

        if (calendar) {
            const now = new Date();
            const year = now.getFullYear();
            const month = now.getMonth() + 1;

            now.setDate(1); // 이번 달의 첫 번째 날짜로 변경한다
            const firstDayOfWeek = now.getDay(); // 첫 번째 날짜의 요일을 가져온다

            calendar.innerHTML = this.makeBaseCalendar(year, month);

            const detailDays = calendar.querySelector(".fos-calendar__days");
            detailDays.innerHTML = this.makeDetailCalendar(month, firstDayOfWeek);

            this.setEventListener();
        } else {
            console.log("BiFOS : calendar 를 생성할 객체가 없습니다! .fos-calendar dom 객체를 생성해주세요!");
        }
    },

    makeBaseCalendar(year, month) {
        return `
            <style>
                .fos-calendar_wrapper {
                    width:100%;
                    border: 1px solid black;
                }
                
                .fos-calendar__title {
                    padding:20px;
                    font-size: 24px;
                    font-weight: bold;
                    text-align: center;
                }
                
                .fos-calendar__day-title, .fos-calendar__days {
                    margin: 0 auto;
                    width:95%; 
                    display: grid; 
                    grid-template-columns: repeat(7, 1fr);
                }
                
                .fos-calendar__day-title :nth-child(6), .fos-calendar__days :nth-child(7n + 6) {
                    color:royalblue;                
                }
                
                .fos-calendar__day-title :nth-child(7), .fos-calendar__days :nth-child(7n) {
                    color:red;
                }
                
                .fos-calendar__day-item {
                    text-align: center;
                    padding-top: 10px;
                    padding-bottom: 10px;
                }
                
                .fos-calendar__day-item[data-selected="true"] {
                    border-radius: 10px;
                    background: cadetblue;
                    color:white;
                    font-weight: bold;
                }
            </style>
            
            <div class="fos-calendar_wrapper">
                <p class="fos-calendar__title" data-year="${year}" data-month="${month}">${month} 월 주문표</p>
                
                <div class="fos-calendar__day-title">
                    <span class="fos-calendar__day-item">월</span>
                    <span class="fos-calendar__day-item">화</span>
                    <span class="fos-calendar__day-item">수</span>
                    <span class="fos-calendar__day-item">목</span>
                    <span class="fos-calendar__day-item">금</span>
                    <span class="fos-calendar__day-item">토</span>
                    <span class="fos-calendar__day-item">일</span>
                </div>
                
                <div class="fos-calendar__days"></div>
            </div>
        `;
    },

    makeDetailCalendar(month, firstDayOfTheWeek) {
        // todo kbt : 윤년 파악도 해야할듯 차차 업그레이드해보자
        const monthDays = [null, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        let returnValue = "";

        for (let i = 0; i < firstDayOfTheWeek; i++) {
            returnValue += `<span class="fos-calendar__day-item"></span>`
        }

        for (let i = 1; i <= monthDays[month]; i++) {
            returnValue += `<span class="fos-calendar__day-item" data-date="${i}">${i}</span>`
        }

        return returnValue;
    },

    setEventListener() {
        // todo kbt : event delegation? 요건 뭘까 더 공부해보자

        const items = document.querySelectorAll(".fos-calendar__day-item");
        Array.from(items).forEach(value => {
            const days = value.textContent;

            // console.log(`BiFoS : ${days} ~ typeOf Days : ${typeof days}`);
            if (days > 0 && days < 32) {
                // console.log(`BiFos : event items?`);
                // console.log(value);
                value.style.cursor = "pointer";
                value.addEventListener("click", this.dayClickListener);
            }
        });
    },

    dayClickListener(event) {
        // console.log(`BiFos : what is this?`);
        // console.log(this);
        if (this.dataset.selected && this.dataset.selected === "true") {
            this.dataset.selected = "false";
        } else {
            this.dataset.selected = "true";
        }
    },

    getSelectedDates() {
        const monthObj = document.querySelector(".fos-calendar__title");
        const selectedDays = document.querySelectorAll(".fos-calendar__day-item[data-selected='true']");

        return {
            "year": monthObj.dataset.year,
            "month": monthObj.dataset.month,
            "dates": Array.from(selectedDays).map(value => value.textContent)
        }
    },

    setData({year, month, date}) {
        // todo kbt : 선택한 연도, 달 변경..
        const monthObj = document.querySelector(".fos-calendar__title");

        const selectedDays = document.querySelector(`.fos-calendar__day-item[data-date="${date}"]`);
        selectedDays.dataset.selected = "true";
    }
}

fosCalendar.init();