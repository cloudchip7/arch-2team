const k8s = require('@kubernetes/client-node');

// const cluster = {
//     name: 'architect-certification-289902-23-dev',
//     server: 'http://server.com',
// };

// const user = {
//     name: 'my-user',
//     password: 'some-password',
// };

// const context = {
//     name: 'my-context',
//     user: user.name,
//     cluster: cluster.name,
// };

// const kc = new k8s.KubeConfig();
// kc.loadFromOptions({
//     clusters: [cluster],
//     users: [user],
//     contexts: [context],
//     currentContext: context.name,
// });


const kc = new k8s.KubeConfig();
kc.loadFromDefault();

const k8sApi = kc.makeApiClient(k8s.CoreV1Api);

k8sApi.listNamespacedPod('default').then((res) => {
    console.log(res.body);
});
