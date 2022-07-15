environment = "dev"
project_id = "architect-certification-289902"
member_id  = "23"
region     = "asia-northeast3"
zones      = ["asia-northeast3-a"]
subnet_cidr = "10.10.0.0/16"
machine_type = "n1-standard-8"

resource "google_container_cluster" "primary" {
  provider = google-beta

  # architect-certification-289902-23-dev
  name     = "${var.project_id}-${var.member_id}-${var.environment}"
  
  # asia-northeast3
  location = var.region

  # asia-northeast3-a
  node_locations = var.zones

  # Node 3대 
  initial_node_count = 3
}

resource "google_container_node_pool" "primary_nodes" {
  name       = "${google_container_cluster.primary.name}-np"
  location   = var.region
  cluster    = google_container_cluster.primary.name
  node_count = 3
} 