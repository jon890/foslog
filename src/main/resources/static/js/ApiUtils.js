const ApiUtils = {
    async request(url, data) {
        try {
            const config = {
                method: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            };

            const response = await fetch(url, config);

            console.log("BiFoS : response");
            console.log(response);

            if (response.ok) {
                return response.json();
            } else {
                throw Error();
            }
        } catch(error) {
            console.log("BiFoS : API 호출 에러 발생!");
            return false;
        }
    }
}