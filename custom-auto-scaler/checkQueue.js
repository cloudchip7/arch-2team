var request = require('request');
const k8s = require('@kubernetes/client-node');

var count_url2 = "http://guest:guest@127.0.0.1:15672/api/queues/%2F/iot.event"
var count_url3 = "http://guest:guest@127.0.0.1:15672/api/queues"
var count_url_local = "http://guest:guest@127.0.0.1:15672/api/overview"

var count_url = "http://guest:guest@rabbitmq-api:15672/api/overview"

// /api/queues/vhost/name/get


let count = 0;
let totalQueueSize = 0;
let currentAverageQueueSize = 0;
let lastAverageQueueSize = 0; 


const kc = new k8s.KubeConfig();
kc.loadFromDefault();
const k8sApi = kc.makeApiClient(k8s.AppsV1Api);

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
function checkRabbit2() { 
    request({
        url : count_url
    }, function(error, response, body) {
        var message = JSON.parse(body); // message.queue_totals.messages = Queue Size
    });
}


let isScaling = false;

async function intervalFunc_old() {
    const currentQueueSize = await checkRabbit();
    console.log("Current Queue Size: " + currentQueueSize);

    if(typeof(currentQueueSize) === "number" )
    {
        count++;
        totalQueueSize += currentQueueSize;
        currentAverageQueueSize = totalQueueSize / count;

        if(count == 5) { 
            count = 0;
            if(baseAverageQueueSize == 0)
                baseAverageQueueSize = currentAverageQueueSize;

            // 10% 이상 평균 Queue Size가 커지면 확장
            const boundary = baseAverageQueueSize + baseAverageQueueSize * 0.1;

            console.log("Average Queue Size: " + currentAverageQueueSize);
            console.log("Scale out boundary: " + boundary);

            if(currentAverageQueueSize > boundary)
            {
                baseAverageQueueSize = currentAverageQueueSize;
     
                const targetNamespaceName = 'default';
                const targetDeploymentName = 'rabbitmq-consumer';
                await scale(targetNamespaceName, targetDeploymentName);

            }
        }
    }
 }

 async function intervalFunc() {
    const currentQueueSize = await checkRabbit();
    console.log("Current Queue Size: " + currentQueueSize);

    if(typeof(currentQueueSize) === "number" )
    {
        count++;
        totalQueueSize += currentQueueSize;
        currentAverageQueueSize = totalQueueSize / count;

        if(count == 10) { 
            count = 0;
            totalQueueSize = 0;
            console.log("Current/Last AverageQueueSize : " + currentAverageQueueSize + "/" + lastAverageQueueSize);
            if(currentAverageQueueSize > 1000) {
                if(currentAverageQueueSize > lastAverageQueueSize){
                    const targetNamespaceName = 'default';
                    const targetDeploymentName = 'rabbitmq-consumer';
                    await scale(targetNamespaceName, targetDeploymentName);
                }
            }

            lastAverageQueueSize = currentAverageQueueSize;
        }
    }
 }


async function scale(namespace, name) {
    // find the particular deployment
    const res = await k8sApi.readNamespacedDeployment(name, namespace);
    let deployment = res.body;

    // edit
    deployment.spec.replicas = deployment.spec.replicas + 1;
    console.log("scale out - replicas number: " + deployment.spec.replicas);

    // replace
    await k8sApi.replaceNamespacedDeployment(name, namespace, deployment);
}


setInterval(intervalFunc, 2000);









