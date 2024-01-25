
class JsonRequest {
    constructor() {
    }

    post(url, obj) {
        return fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(obj)
        });

    }
    get(url){
        fetch(url).then(response => response.json());
    }
}
export {JsonRequest};