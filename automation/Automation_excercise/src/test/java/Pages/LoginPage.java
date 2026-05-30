package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    // ─── Locators — Login ────────────────────────────────────────────────────────
    /** Champ email du formulaire Login */
    public final Locator email;

    /** Champ mot de passe du formulaire Login */
    public final Locator password;

    /** Bouton de soumission du formulaire Login */
    public final Locator loginBtn;

    /** Message d'erreur affiché en cas d'identifiants incorrects */
    public final Locator errorMsg;

    /** Texte "Logged in as <username>" visible après connexion réussie */
    public final Locator username;

    /** Lien de déconnexion dans la barre de navigation */
    public final Locator logoutBtn;

    // ─── Locators — Signup (étape 1) ─────────────────────────────────────────────
    /** Champ nom du formulaire "New User Signup!" */
    public final Locator signupName;

    /** Champ email du formulaire "New User Signup!" */
    public final Locator signupMail;

    /** Bouton de soumission du formulaire Signup */
    public final Locator signupBtn;

    // ─── Locators — Inscription (étape 2 : ENTER ACCOUNT INFORMATION) ────────────
    /** Conteneur du formulaire d'inscription complet */
    public final Locator form;

    /** Champ mot de passe du formulaire de création de compte */
    public final Locator createPassword;

    /** Champ prénom */
    public final Locator firstName;

    /** Champ nom de famille */
    public final Locator lastName;

    /** Champ adresse principale */
    public final Locator address;

    /** Champ état / région */
    public final Locator state;

    /** Champ ville */
    public final Locator city;

    /** Champ code postal */
    public final Locator zipcode;

    /** Champ numéro de téléphone mobile */
    public final Locator phone;

    /** Bouton de création du compte */
    public final Locator createAccountBtn;

    // ─── Page ────────────────────────────────────────────────────────────────────
    private final Page page;

    // ─── Constructeur ─────────────────────────────────────────────────────────────
    /**
     * Initialise tous les locators de la page Login et Inscription.
     *
     * @param page Instance Playwright Page injectée depuis le test
     */
    public LoginPage(Page page) {
        this.page = page;

        // Login
        this.email    = page.locator("input[data-qa='login-email']");
        this.password = page.locator("input[data-qa='login-password']");
        this.loginBtn = page.locator("button[data-qa='login-button']");
        this.errorMsg = page.getByText("Your email or password is incorrect!");
        this.username = page.getByText("Logged in as Mervin");
        this.logoutBtn = page.locator("a:has-text('Logout')");

        // Signup étape 1
        this.signupName = page.locator("//input[@data-qa='signup-name']");
        this.signupMail = page.locator("//input[@data-qa='signup-email']");
        this.signupBtn  = page.locator("button[data-qa='signup-button']");

        // Inscription étape 2
        this.form             = page.locator(".login-form");
        this.createPassword   = page.locator("#password");
        this.firstName        = page.locator("#first_name");
        this.lastName         = page.locator("#last_name");
        this.address          = page.locator("#address1");
        this.state            = page.locator("#state");
        this.city             = page.locator("#city");
        this.zipcode          = page.locator("#zipcode");
        this.phone            = page.locator("#mobile_number");
        this.createAccountBtn = page.locator("button[data-qa='create-account']");

        logger.debug("🔧 LoginPage initialisée — tous les locators chargés");
    }

    // ─── Navigation ───────────────────────────────────────────────────────────────
    /**
     * Navigue vers la page Login de l'application.
     * Utilisé comme première étape dans tous les tests d'authentification.
     */
    public void navigateTo() {
        String url = "https://automationexercise.com/login";
        logger.info("🌐 Navigation vers : {}", url);
        page.navigate(url);
        logger.info("✅ Page Login chargée");
    }

    // ─── Méthode utilitaire privée ────────────────────────────────────────────────
    /**
     * Remplit un champ texte avec la valeur fournie.
     * Méthode interne utilisée par toutes les méthodes enter*() et fillInfo().
     *
     * @param locator Champ cible
     * @param text    Valeur à saisir
     */
    private void sendText(Locator locator, String text) {
        locator.fill(text);
        logger.debug("✏️ Champ '{}' rempli avec : '{}'", locator, text);
    }

    // ─── Actions — Login ──────────────────────────────────────────────────────────
    /**
     * Saisit l'email dans le champ Login.
     *
     * @param mail Email de l'utilisateur
     */
    public void enterMail(String mail) {
        logger.info("✏️ Saisie email login : '{}'", mail);
        sendText(email, mail);
    }

    /**
     * Saisit le mot de passe dans le champ Login.
     *
     * @param passWord Mot de passe de l'utilisateur
     */
    public void enterPassword(String passWord) {
        logger.info("✏️ Saisie mot de passe login");
        sendText(password, passWord);
    }

    /**
     * Clique sur le bouton Login pour soumettre le formulaire.
     */
    public void clickLoginBtn() {
        logger.info("🖱️ Clic sur le bouton Login");
        clickBtn(loginBtn);
    }

    /**
     * Clique sur le lien Logout dans la barre de navigation.
     */
    public void clickLogoutBtn() {
        logger.info("🖱️ Clic sur le bouton Logout");
        clickBtn(logoutBtn);
    }

    // ─── Actions — Signup étape 1 ─────────────────────────────────────────────────
    /**
     * Saisit le nom dans le champ "New User Signup!".
     *
     * @param name Nom complet du nouvel utilisateur
     */
    public void enterSignupName(String name) {
        logger.info("✏️ Saisie nom signup : '{}'", name);
        sendText(signupName, name);
    }

    /**
     * Saisit l'email dans le champ "New User Signup!".
     *
     * @param mail Email du nouvel utilisateur
     */
    public void enterSignupMail(String mail) {
        logger.info("✏️ Saisie email signup : '{}'", mail);
        sendText(signupMail, mail);
    }

    /**
     * Clique sur le bouton Signup pour accéder au formulaire d'inscription complet.
     */
    public void clickSignupBtn() {
        logger.info("🖱️ Clic sur le bouton Signup");
        clickBtn(signupBtn);
    }

    // ─── Actions — Inscription étape 2 ───────────────────────────────────────────
    /**
     * Remplit tous les champs obligatoires du formulaire "ENTER ACCOUNT INFORMATION".
     * Les champs optionnels (Company, Address2) sont gérés séparément.
     *
     * @param password  Mot de passe du compte
     * @param firstname Prénom
     * @param lastname  Nom de famille
     * @param Address   Adresse principale
     * @param State     État / Région
     * @param City      Ville
     * @param zipCode   Code postal
     * @param Phone     Numéro de téléphone mobile
     */
    public void fillInfo(String password, String firstname, String lastname,
                         String Address, String State, String City,
                         String zipCode, String Phone) {
        logger.info("✏️ Remplissage des champs obligatoires du formulaire d'inscription");
        sendText(createPassword, password);
        sendText(firstName, firstname);
        sendText(lastName, lastname);
        sendText(address, Address);
        sendText(state, State);
        sendText(city, City);
        sendText(zipcode, zipCode);
        sendText(phone, Phone);
        logger.info("✅ Tous les champs obligatoires remplis avec succès");
    }

    /**
     * Clique sur le bouton "Create Account" pour finaliser l'inscription.
     */
    public void clickCreateAccountBtn() {
        logger.info("🖱️ Clic sur le bouton Create Account");
        clickBtn(createAccountBtn);
    }

    // ─── Méthode utilitaire publique ──────────────────────────────────────────────
    /**
     * Clique sur le locator fourni.
     * Méthode générique réutilisable pour tout bouton ou lien cliquable.
     *
     * @param locator Élément cible à cliquer
     */
    public void clickBtn(Locator locator) {
        locator.click();
        logger.debug("🖱️ Clic effectué sur : {}", locator);
    }
}