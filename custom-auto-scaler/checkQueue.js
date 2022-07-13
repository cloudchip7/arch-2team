var request = require('request');
var count_url2 = "http://guest:guest@127.0.0.1:15672/api/queues/%2F/iot.event"
var count_url3 = "http://guest:guest@127.0.0.1:15672/api/queues"
var count_url_local = "http://guest:guest@127.0.0.1:15672/api/overview"

var count_url = "http://guest:guest@rabbitmq:15672/api/overview"

// /api/queues/vhost/name/get

function checkRabbit() { 
    return new Promise(function (resolve, reject) { 
        request({
            url : count_url
        }, function(error, response, body) {
            if (error) {
                reject("failed");
            }
            else
            {
                var message = JSON.parse(body);
                resolve(message.queue_totals.messages);
            }
        });
    });
}

async function intervalFunc() {
    const data = await checkRabbit();
    console.log(data);
  }
  
setInterval(intervalFunc, 1500);


