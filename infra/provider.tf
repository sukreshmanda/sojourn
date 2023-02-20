#https://registry.terraform.io/providers/hashicorp/google/latest/docs

provider "google" {
  region  = "asia-south1"
  project = "sojourn-378405"
}

terraform {
  backend "gcs" {
    bucket = "sojourn-bucket"
    prefix = "terraform/state"
  }
}
