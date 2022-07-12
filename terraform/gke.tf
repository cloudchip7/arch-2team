# GKE cluster
resource "google_container_cluster" "primary" {
  provider = google-beta

  #GKE 이름에 40자 이상 사용할 수 없는 제약사항 추가되었음
  #name     = "ca-${var.member_id}-${var.environment}"
  name     = "${var.project_id}-${var.member_id}-${var.environment}"
  location = var.region
  node_locations = var.zones

  remove_default_node_pool = true
  initial_node_count       = 1

  network    = google_compute_network.vpc.name
  subnetwork = google_compute_subnetwork.subnet.name

  master_auth {
    client_certificate_config {
      issue_client_certificate = false
    }
  }

  addons_config {
    istio_config {
      disabled = false
      auth     = "AUTH_MUTUAL_TLS"
    }
  }
}

# Separately Managed Node Pool
resource "google_container_node_pool" "primary_nodes" {
  name       = "${google_container_cluster.primary.name}-np"
  location   = var.region
  cluster    = google_container_cluster.primary.name
  node_count = var.gke_num_nodes

  node_config {
    oauth_scopes = [
      "https://www.googleapis.com/auth/logging.write",
      "https://www.googleapis.com/auth/monitoring",
      "https://www.googleapis.com/auth/compute",
      "https://www.googleapis.com/auth/trace.append",
      "https://www.googleapis.com/auth/devstorage.read_only"
    ]

    labels = {
      env = var.project_id
    }

    # preemptible  = true
    machine_type = var.machine_type
    tags         = ["gke-node", "${var.project_id}-${var.member_id}-${var.environment}"]
    metadata = {
      disable-legacy-endpoints = "true"
    }
  }
}

resource "kubernetes_namespace" "team2-dev" {
 
  metadata {
    annotations      = {}
    labels           = {}
    name = "team2-dev"
  }
}