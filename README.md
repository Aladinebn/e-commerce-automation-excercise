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

## 🚀 Sprint 1 — Authentication Module

### Périmètre couvert

| TC | Titre | Squash TM (Manuel) | Automatisé |
|----|-------|-------------------|-----------|
| TC-L01 | Login valide | ✅ Passed | ✅ |
| TC-L02 | Login — MDP incorrect | ✅ Passed | ✅ |
| TC-L03 | Login — Email format invalide | ✅ Passed | ✅ |
| TC-L04 | Login — Champs vides | ✅ Passed | ❌ |
| TC-L05 | Login — Email vide uniquement | ✅ Passed | ❌ |
| TC-L06 | Login — MDP vide uniquement | ✅ Passed | ❌ |
| TC-L07 | Logout | ✅ Passed | ✅ |
| TC-L08 | Retour navigateur après logout | ✅ Passed | ❌ |
| TC-L09 | Inscription — Flux complet | ✅ Passed | ✅ |
| TC-L10 | Inscription — Sans newsletter | ✅ Passed | ❌ |
| TC-L11 | Inscription — Champs optionnels vides | ✅ Passed | ❌ |
| TC-L12 | Inscription — Email déjà existant | ✅ Passed | ❌ |
| TC-L13 | Inscription — Champ nom vide | ✅ Passed | ❌ |
| TC-L14 | Inscription — Champ email vide | ✅ Passed | ❌ |
| TC-L15 | Inscription — Email format invalide | ✅ Passed | ❌ |
| TC-L16 | Inscription — MDP vide | ✅ Passed | ❌ |
| TC-L17 | Inscription — Adresse obligatoire vide | ✅ Passed | ❌ |

> ✅ **17/17 cas de test exécutés manuellement sur Squash TM**
> ✅ **5/17 cas de test automatisés (TC critiques)**

### Outils utilisés — Sprint 1

| Outil | Usage |
|-------|-------|
| **Squash TM** | Exécution manuelle des 17 TC — Campagne `CAMP-1 / Exécution manuelle complète` |
| **Jira** | Suivi des US (US-01 / US-02 / US-03) et tasks (TK-01 à TK-06) |
| **Playwright + Java** | Automatisation des 5 TC critiques |

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
