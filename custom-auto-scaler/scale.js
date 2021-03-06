const k8s = require('@kubernetes/client-node');

const kc = new k8s.KubeConfig();
kc.loadFromDefault();

const k8sApi = kc.makeApiClient(k8s.AppsV1Api);

const targetNamespaceName = 'default';
const targetDeploymentName = 'custom-auto-scaler';
const numberOfTargetReplicas = 3;


async function scale(namespace, name) {
  // find the particular deployment
  const res = await k8sApi.readNamespacedDeployment(name, namespace);
  let deployment = res.body;

  console.log("current replicas number: " + deployment.spec.replicas);
  // edit
  deployment.spec.replicas = deployment.spec.replicas + 1;

  // replace
  await k8sApi.replaceNamespacedDeployment(name, namespace, deployment);
}

// scale(targetNamespaceName, targetDeploymentName, numberOfTargetReplicas);