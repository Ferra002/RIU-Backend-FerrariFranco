import http from 'k6/http'
import { check } from 'k6'

export const options = {
  vus: 100,
  duration: '30s',
}

export default function () {
    const payload = JSON.stringify({
        hotelId: '1234aBc',
        checkIn: '2026-08-24',
        checkOut: '2026-09-24',
        ages: [59, 54, 24]
    });

    const response = http.post(
        'http://localhost:3500/api/hotel-search/v1/search',
        payload,
        {
            headers: {
                'Content-Type': 'application/json',
            }
        }
    );

    check(response, {
        'status is 201': (r) => r.status === 201,
        'has searchId': (r) => r.json('searchId') != null
    });

}