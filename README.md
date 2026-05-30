# 🧪 Automation Exercise — QA Project

Projet de test complet (manuel + automatisé) réalisé sur l'application
[Automation Exercise](https://automationexercise.com/) dans le cadre d'une
montée en compétences en **Assurance Qualité Logicielle**.

---

## 🎯 Objectif

Mettre en pratique l'ensemble du cycle de test QA :
de la rédaction du plan de test jusqu'à l'automatisation des scénarios critiques,
en passant par la conception BDD, la gestion sur Jira et l'organisation sur Squash TM.

---

## 🗂️ Structure du projet
---

## 🚀 Sprint 1 — Authentication Module

### Périmètre couvert

| Fonctionnalité | Manuel | Automatisé |
|----------------|--------|-----------|
| Login valide | ✅ | ✅ |
| Login — MDP incorrect | ✅ | ✅ |
| Login — Email invalide (HTML5) | ✅ | ✅ |
| Logout | ✅ | ✅ |
| Inscription — Flux complet | ✅ | ✅ |
| Inscription — Email déjà existant | ✅ | ❌ |
| Inscription — Champs obligatoires vides | ✅ | ❌ |

### Cas de test automatisés

| TC | Titre | Statut |
|----|-------|--------|
| TC-L01 | Login valide | ✅ |
| TC-L02 | Login — MDP incorrect | ✅ |
| TC-L03 | Login — Email format invalide | ✅ |
| TC-L07 | Logout | ✅ |
| TC-L09 | Inscription flux complet | ✅ |

---

## 🛠️ Stack technique

| Outil | Rôle |
|-------|------|
| **Playwright + Java** | Framework d'automatisation UI |
| **JUnit 5** | Runner de tests |
| **SLF4J / Logback** | Logging structuré |
| **Page Object Model** | Pattern de conception des pages |
| **Maven** | Gestion des dépendances |
| **Squash TM** | Gestion des cas de test et campagnes |
| **Jira** | Suivi des US, tasks et bugs |

---

## ⚙️ Prérequis

- Java 17+
- Maven 3.8+
- Navigateur Chromium (installé automatiquement par Playwright)

---

## ▶️ Lancer les tests

```bash
# Cloner le projet
git clone https://github.com/Aladinebn/e-commerce-automation-excercise.git

# Accéder au module automation
cd automation/Automation_excercise

# Lancer tous les tests
mvn test

# Lancer un test spécifique
mvn test -Dtest=AuthTests#validLogin
```

---

## 📁 Artefacts QA

| Artefact | Emplacement |
|----------|-------------|
| Plan de test | `/test_plan/` |
| Cas de test BDD | `/test_cases/` |
| Scénarios | `/test_scenarios/` |
| Résultats manuels | `/manual_testing/` |

---

## 🗓️ Roadmap

- [x] Sprint 1 — Authentication (Login / Logout / Inscription)
- [ ] Sprint 2 — Produits & Catalogue
- [ ] Sprint 3 — Panier & Commande
- [ ] Sprint 4 — Newsletter & Navigation
- [ ] Sprint 5 — Intégration CI/CD (GitHub Actions)

---

## 👤 Auteur

**Aladine BN** — QA Engineer en formation
[GitHub](https://github.com/Aladinebn)

---

> Projet réalisé à des fins d'apprentissage sur
> [automationexercise.com](https://automationexercise.com/)
