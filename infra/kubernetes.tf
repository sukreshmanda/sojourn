resource "google_container_cluster" "sojourn-cluster" {
  name                     = "sojourn-cluster"
  location                 = "asia-south1-a"
  remove_default_node_pool = true
  initial_node_count       = 1
  network                  = google_compute_network.sojourn-vpc.self_link
  subnetwork               = google_compute_subnetwork.sojourn-subnet.self_link
  #   logging_service          = "logging.googleapis.com/kubernetes"
  #   monitoring_service = "monitoring.googleapis.com/kubernetes"
  networking_mode = "VPC_NATIVE"

  ip_allocation_policy {
    clustcluster_secondary_range_name = "k8s-pod-range"
    services_secondary_range_name     = "k8s-service-range"
  }

  private_cluster_config {
    enable_private_nodes    = true
    enable_private_endpoint = false
    master_ipv4_cidr_block  = "172.16.0.0/28"
  }
}
