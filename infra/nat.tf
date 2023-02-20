resource "google_compute_router_nat" "sojourn-nat" {
  name   = "sojourn-nat"
  router = google_compute_router.sojourn-router.name
  region = "asia-south1"

  source_subnetwork_ip_ranges_to_nat = "LIST_OF_SUBNETWORKS"
  nat_ip_allocate_option             = "MANUAL_ONLY"

  subnetwork {
    name                    = google_compute_subnetwork.sojourn-subnet.id
    source_ip_ranges_to_nat = ["ALL_IP_RANGES"]
  }
  nat_ips = [google_compute_address.nat.self_link]
}

resource "google_compute_address" "sojourn-nat" {
  name         = "sojourn-nat"
  address_type = "EXTERNAL"
  network_tier = "STANDARD"

  depends_on = [
    google_project_service.compute
  ]
}
