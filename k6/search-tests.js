import http from 'k6/http'
import { check } from 'k6'

export const options = {
  vus: 100,
  duration: '30s',
}

export default function () {
    const search = {
        hotelId: '1234aBc',
        checkIn: '2026-08-24',
        checkOut: '2026-09-24',
        ages: [59, 54, 24]
    };

    const payload = JSON.stringify(search);

    const postResponse = http.post(
        'http://localhost:3500/search',
        payload,
        {
            headers: {
                'Content-Type': 'application/json',
            }
        }
    );

    const searchId = postResponse.json('searchId');

    check(postResponse, {
        'status is 201': (r) => r.status === 201,
        'has searchId': () => searchId != null,
    });

    const getResponse = http.get(
        `http://localhost:3500/count?searchId=${searchId}`
    );

    const getResponseBody = getResponse.json();

    check(getResponseBody, {
        'status is 200': (r) => r.status === 200,
        'has searchId': () => getResponseBody.searchId != null,
        'is searchId same': () => getResponseBody.searchId === searchId,
        'has search': () => getResponseBody.search != null,
        'has hotelId': () => getResponseBody.search.hotelId != null,
        'is hotelId same': () => getResponseBody.hotelId === search.hotelId,
        'has checkIn': () => getResponseBody.search.checkIn != null,
        'is checkIn same': () => getResponseBody.checkIn === search.checkIn,
        'has checkOut': () => getResponseBody.search.checkOut != null,
        'is checkOut same': () => getResponseBody.checkOut === search.checkOut,
        'has ages': () => getResponseBody.search.ages != null,
        'is ages same': () =>
            JSON.stringify(getResponseBody.ages) === JSON.stringify(search.ages),
        'has count': () => getResponseBody.count != null
    });

}