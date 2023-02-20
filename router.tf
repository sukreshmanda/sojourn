resource "google_compute_router" "sojourn-router" {
  name    = "sojourn-router"
  region  = "asia-south1"
  network = google_compute_network.sojourn-vpc.id
}
