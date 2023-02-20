resource "google_service_account" "sojourn-kubernetes" {
  account_id = "kubernetes"
}

resource "google_container_node_pool" "spot-node-pool" {
  name       = "spot-node-pool"
  cluster    = google_container_cluster.sojourn-cluster.id
  node_count = 1

  management {
    auto_repair  = true
    auto_upgrade = true
  }
  autoscaling {
    min_node_count = 0
    max_node_count = 3
  }
  node_config {
    preemptible  = true
    machine_type = "e2-small"
    labels = {
      type = "spot-node-pool"
    }
    taint {
      key    = "instance_type"
      value  = "spot"
      effect = "NO_SCHEDULE"
    }
    service_account = google_service_account.sojourn-kubernetes.email
    oauth_scopes = [
      "https://www.googleapi.com/auth/cloud-platform"
    ]
  }
}
