# TP1 : Modélisation XML et Interrogation XPath avec Maven en Java

**Université :** Faculté des Sciences de Sfax  
**Filière :** LSI3  
**Matière :** Architecture SOA et Services Web  
**Année Universitaire :** 2025-2026

---

## 📌 Description du Projet

Ce projet illustre la modélisation et la validation de données XML à l'aide d'un schéma XSD, ainsi que l'interrogation de ces données via des expressions XPath en Java dans un environnement structuré avec Maven.

### Domaine choisi : **E-Commerce (Catalogue de Produits)**
Le domaine choisi représente la gestion d'un catalogue de produits informatiques et d'équipements pour un magasin en ligne.

---

## 📄 Fichiers de Modélisation

### 1. Document XML (`src/main/resources/ecommerce.xml`)
Contient la liste des produits avec leurs attributs (`id`) et leurs éléments (`Nom`, `Prix`, `Categorie`, `EnPromotion`, `Stock`).

### 2. Schéma XSD (`src/main/resources/ecommerce.xsd`)
Définit la structure valide du fichier XML :
* **Élément racine :** `<Catalogue>`
* **Élément répété :** `<Produit>` avec l'attribut obligatoire `id`
* **Types de données :** `xs:string`, `xs:decimal`, `xs:boolean`, `xs:integer`

---

## 🔍 Exemples de Requêtes XPath Implémentées

Dans la classe `Main.java`, deux requêtes XPath principales sont exécutées :

1. **Extraction de tous les noms de produits :**
    * **Expression XPath :** `//Produit/Nom`
    * **Objectif :** Récupérer la liste complète des noms de tous les articles du catalogue.

2. **Extraction des produits en promotion :**
    * **Expression XPath :** `//Produit[EnPromotion='true']/Nom`
    * **Objectif :** Filtrer et afficher uniquement les noms des produits dont la balise `<EnPromotion>` vaut `true`.

---

