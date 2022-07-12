var request = require('request');
var count_url2 = "http://guest:guest@127.0.0.1:15672/api/queues/%2F/iotQueue"
var count_url = "http://guest:guest@127.0.0.1:15672/api/vhosts/" 

// /api/queues/vhost/name/get

var mincount = 0;

request({
    url : count_url2
}, function(error, response, body) {
    console.log("Called RabbitMQ API");
    if (error) {
        console.error("Unable to fetch Queued Msgs Count" + error);
        return;
    }
    else
    {
        var message = JSON.parse(body);
        console.log(message);

        // if (message.hasOwnProperty("messages_ready")) {
        //     // this DOES NOT COUNT UnAck msgs
        //     var msg_ready = JSON.stringify(message.messages_ready);
        //     console.log("message.messages_ready=" + msg_ready);
        //     if (msg_ready == mincount) {
        //         console.log("mincount Reached ..Requesting Producer");
        //         ///Code to Produce msgs  ..
        //     }
        // }
        // if (message.hasOwnProperty("messages")) {
        //     // _messages_ total messages i.e including unAck
        //     var msg = JSON.stringify(message.messages);
        //     console.log("message.messages=" + msg);
        // }
    }
});