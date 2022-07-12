var amqp = require('amqplib/callback_api');

var count = 0;

amqp.connect('amqp://localhost', function(error0, connection) {
    if (error0) {
        throw error0;
    }
    connection.createChannel(function(error1, channel) {
        if (error1) {
            throw error1;
        }

        var queue = 'iotQueue';

        // channel.assertQueue(queue, {
        //     durable: false
        // });

        console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);

        channel.consume(queue, function(msg) {
            // const item = JSON.parse(msg.content.toString());
            // let currentTime = new Date() - new Date(item.date) + 1;
            // console.log(currentTime);
        }, {
            noAck: false
        });
    });
});