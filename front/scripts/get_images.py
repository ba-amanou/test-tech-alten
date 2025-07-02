import os
import json
import requests
from urllib.parse import urlparse

#script python permettant de récupérer les images de assets/products.json
#et de les importer dans le dossier assets/images

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

#fichier contenant les produits
products_json_path = os.path.join(BASE_DIR,"../src/assets/products.json")
#fichier destination pour les images
save_folder = os.path.join(BASE_DIR,"../src/assets/images")
#prefixe url pour récupérer les images
url_prefix = "https://primefaces.org/cdn/primeng/images/demo/product/"

os.makedirs(save_folder, exist_ok=True)

with open(products_json_path, "r", encoding="utf-8") as f:
    products = json.load(f)

# Telechargement des images
for product in products:
    image_name = product.get("image")
    if image_name:
        image_url = url_prefix + image_name  # Concaténation de l'URL complète
        filename = image_name
        filepath = os.path.join(save_folder, filename)

        print(f"Téléchargement de {image_url} vers {filepath} ...")
        try:
            response = requests.get(image_url, timeout=10)
            response.raise_for_status()
            with open(filepath, "wb") as img_file:
                img_file.write(response.content)
        except Exception as e:
            print(f"Erreur lors du téléchargement de {image_url}: {e}")
    else:
        print("Aucune image trouvée pour ce produit.")
