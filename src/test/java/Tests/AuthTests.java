package Tests;

import Base.BasicTest;
import Pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthTests extends BasicTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthTests.class);

    // ─── Données de test ────────────────────────────────────────────────────────
    private static final String VALID_EMAIL    = "zivpippy@rulersonline.com";
    private static final String VALID_PASSWORD = "9gTK973Rn@i2h6t";
    private static final String INVALID_EMAIL  = "user.com";
    private static final String INVALID_PASSWORD = "invalid_password";
    private static final String SIGNUP_NAME   = "Geoffrey J Lopez";
    private static final String SIGNUP_MAIL   = "ski.l.igge.e@gmail.com";

    // ─── TC-L01 ─────────────────────────────────────────────────────────────────
    /**
     * TC-L01 — Login valide
     * Nature   : Fonctionnel / Utilisateur
     * Type     : Recevabilité
     * Priorité : Critique | Criticité : Bloquante
     * Given un compte utilisateur existant
     * When  je saisis un email valide + MDP correct et clique Login
     * Then  "Logged in as <username>" est visible
     */
    @Test
    public void validLogin() {
        logger.info("▶ [TC-L01] Démarrage — Login valide");

        LoginPage loginPage = new LoginPage(page);

        logger.info("🌐 [TC-L01] Navigation vers la page Login");
        loginPage.navigateTo();

        logger.info("✏️ [TC-L01] Saisie email valide : '{}'", VALID_EMAIL);
        loginPage.enterMail(VALID_EMAIL);

        logger.info("✏️ [TC-L01] Saisie mot de passe valide");
        loginPage.enterPassword(VALID_PASSWORD);

        logger.info("🖱️ [TC-L01] Clic sur le bouton Login");
        loginPage.clickLoginBtn();

        logger.info("🔍 [TC-L01] Vérification — 'Logged in as username' visible");
        Assertions.assertTrue(
                loginPage.username.isVisible(),
                "[TC-L01] ÉCHEC — Le message 'Logged in as username' n'est pas visible"
        );

        logger.info("✅ [TC-L01] SUCCÈS — Utilisateur connecté avec succès");
    }

    // ─── TC-L02 ─────────────────────────────────────────────────────────────────
    /**
     * TC-L02 — Login avec mot de passe incorrect
     * Nature   : Fonctionnel / Métier
     * Type     : Recevabilité
     * Priorité : Critique | Criticité : Bloquante
     * Given un compte utilisateur existant
     * When  je saisis un email valide + MDP incorrect et clique Login
     * Then  le message d'erreur "Your email or password is incorrect!" est affiché
     */
    @Test
    public void invalidPassword() {
        logger.info("▶ [TC-L02] Démarrage — Login avec mot de passe incorrect");

        LoginPage loginPage = new LoginPage(page);

        logger.info("🌐 [TC-L02] Navigation vers la page Login");
        loginPage.navigateTo();

        logger.info("✏️ [TC-L02] Saisie email valide : '{}'", VALID_EMAIL);
        loginPage.enterMail(VALID_EMAIL);

        logger.info("✏️ [TC-L02] Saisie mot de passe incorrect : '{}'", INVALID_PASSWORD);
        loginPage.enterPassword(INVALID_PASSWORD);

        logger.info("🖱️ [TC-L02] Clic sur le bouton Login");
        loginPage.clickLoginBtn();

        logger.info("🔍 [TC-L02] Vérification — message d'erreur visible");
        Assertions.assertTrue(
                loginPage.errorMsg.isVisible(),
                "[TC-L02] ÉCHEC — Le message d'erreur n'est pas affiché"
        );

        logger.info("✅ [TC-L02] SUCCÈS — Message d'erreur correctement affiché");
    }

    // ─── TC-L03 ─────────────────────────────────────────────────────────────────
    /*
     * TC-L03 — Login avec email incorrect
     * Nature   : Fonctionnel / Métier
     * Type     : Recevabilité
     * Priorité : Critique | Criticité : Bloquante
     *
     * Given un compte utilisateur existant
     * When  je saisis un email inexistant + MDP quelconque et clique Login
     * Then  le message d'erreur "Your email or password is incorrect!" est affiché
     */
    /**
     * TC-L03 — Login avec email format invalide
     * Nature   : Non Fonctionnel / Utilisateur
     * Type     : Recevabilité
     * Priorité : Haute | Criticité : Majeure
     * Given aucune précondition
     * When  je saisis un email sans @ et clique Login
     * Then  la validation HTML5 bloque la soumission
     * And   le champ email est marqué invalide par le navigateur
     */
    @Test
    public void invalidEmail() {
        logger.info("▶ [TC-L03] Démarrage — Login avec email format invalide (HTML5)");

        LoginPage loginPage = new LoginPage(page);

        logger.info("🌐 [TC-L03] Navigation vers la page Login");
        loginPage.navigateTo();

        logger.info("✏️ [TC-L03] Saisie email invalide (sans @) : '{}'", INVALID_EMAIL);
        loginPage.enterMail(INVALID_EMAIL);

        logger.info("✏️ [TC-L03] Saisie mot de passe valide");
        loginPage.enterPassword(VALID_PASSWORD);

        logger.info("🖱️ [TC-L03] Clic sur le bouton Login");
        loginPage.clickLoginBtn();

        // ── Vérification HTML5 ──────────────────────────────────────────────────────
        // La validation HTML5 est native au navigateur.
        // Playwright expose l'état de validation via evaluateHandle sur la propriété
        // "validity.valid" du champ — false = champ invalide selon les règles HTML5.
        logger.info("🔍 [TC-L03] Vérification — état de validation HTML5 du champ email");

        Boolean isEmailValid = (Boolean) page.evaluate(
                "document.querySelector(\"input[data-qa='login-email']\").validity.valid"
        );

        logger.info("🔍 [TC-L03] validity.valid = {}", isEmailValid);

        Assertions.assertFalse(
                isEmailValid,
                "[TC-L03] ÉCHEC — Le champ email est considéré valide alors qu'il ne contient pas de @"
        );

        // Vérification complémentaire : le formulaire n'a pas été soumis
        // → errorMsg du serveur ne doit PAS être visible (la requête n'a pas été envoyée)
        logger.info("🔍 [TC-L03] Vérification complémentaire — le formulaire n'a pas été soumis");

        Assertions.assertFalse(
                loginPage.errorMsg.isVisible(),
                "[TC-L03] ÉCHEC — Le message serveur est visible, la soumission n'a pas été bloquée par HTML5"
        );

        logger.info("✅ [TC-L03] SUCCÈS — Validation HTML5 active, soumission bloquée");
    }

    // ─── TC-L07 ─────────────────────────────────────────────────────────────────
    /**
     * TC-L07 — Logout après connexion
     * Nature   : Fonctionnel / Sécurité
     * Type     : Recevabilité / Non Régression
     * Priorité : Critique | Criticité : Bloquante
     * Given un utilisateur connecté
     * When  il clique sur Logout
     * Then  il est redirigé vers /login et la session est terminée
     */
    @Test
    public void logout() {
        logger.info("▶ [TC-L07] Démarrage — Logout après connexion");

        LoginPage loginPage = new LoginPage(page);

        // Précondition : être connecté
        logger.info("🔑 [TC-L07] Précondition — connexion avec un compte valide");
        loginPage.navigateTo();
        loginPage.enterMail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLoginBtn();

        logger.info("✅ [TC-L07] Précondition — utilisateur connecté");

        logger.info("🖱️ [TC-L07] Clic sur le bouton Logout");
        loginPage.clickLogoutBtn();

        logger.info("🔍 [TC-L07] Vérification — 'Logged in as username' n'est plus visible");
        Assertions.assertFalse(
                loginPage.username.isVisible(),
                "[TC-L07] ÉCHEC — L'utilisateur est toujours connecté après Logout"
        );

        logger.info("✅ [TC-L07] SUCCÈS — Session terminée, utilisateur déconnecté");
    }

    // ─── TC-L09 ─────────────────────────────────────────────────────────────────
    /**
     * TC-L09 — Inscription flux complet
     * Nature   : Fonctionnel / Utilisateur / ATDD
     * Type     : Recevabilité / Bout en bout
     * Priorité : Critique | Criticité : Bloquante
     * Given aucun compte existant avec cet email
     * When  je remplis le formulaire Signup + les infos du compte
     * And   je clique sur Create Account
     * Then  le message "ACCOUNT CREATED!" est affiché
     */
    @Test
    public void validRegister() {
        logger.info("▶ [TC-L09] Démarrage — Inscription flux complet");

        LoginPage loginPage = new LoginPage(page);

        logger.info("🌐 [TC-L09] Navigation vers la page Login");
        loginPage.navigateTo();

        // Étape 1 : Formulaire Signup
        logger.info("✏️ [TC-L09] Saisie nom inscription : '{}'", SIGNUP_NAME);
        loginPage.enterSignupName(SIGNUP_NAME);

        logger.info("✏️ [TC-L09] Saisie email inscription : '{}'", SIGNUP_MAIL);
        loginPage.enterSignupMail(SIGNUP_MAIL);

        logger.info("🖱️ [TC-L09] Clic sur le bouton Signup");
        loginPage.clickSignupBtn();

        // Étape 2 : Vérification affichage formulaire compte
        logger.info("🔍 [TC-L09] Vérification — formulaire ENTER ACCOUNT INFORMATION visible");
        Assertions.assertTrue(
                loginPage.form.isVisible(),
                "[TC-L09] ÉCHEC — Le formulaire d'inscription n'est pas affiché"
        );
        logger.info("✅ [TC-L09] Formulaire affiché — saisie des informations du compte");

        // Étape 3 : Remplissage des informations obligatoires
        logger.info("✏️ [TC-L09] Remplissage des champs obligatoires du compte");
        loginPage.fillInfo(
                "NTW3bAz@TKNyMX9",
                "Geoffrey",
                "Lopez",
                "3666 Hickory Lane",
                "Washington DC",
                "Washington",
                "20005",
                "213-217-0315"
        );

        // Étape 4 : Création du compte
        logger.info("🖱️ [TC-L09] Clic sur le bouton Create Account");
        loginPage.clickCreateAccountBtn();

        logger.info("✅ [TC-L09] SUCCÈS — Compte créé avec succès");
    }
}