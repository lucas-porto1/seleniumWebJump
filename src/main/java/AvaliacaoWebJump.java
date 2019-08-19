
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * Classe contendo todos os testes propostos na avaliação da empresa WebJump.
 *
 * @author Lucas Porto Gomes
 */


public class AvaliacaoWebJump {


    private WebDriver driver;

    @Before
    public void inicializa() {
        //configuração do ambiente.
        driver = new FirefoxDriver(); // Apontando o driver para o firefox.
        driver.manage().window().maximize(); // Alterando tamanho da janela do navegador.
        driver.get("https://webjump-user.github.io/testqa/"); // Apontando endereço que será utilizado no teste.

    }

    @After
    public void finaliza() {
        driver.quit();
    }


    @Test
    public void testeButtons() {

        // Verifica se o botão one está presente antes de ser pressionado.
        Assert.assertTrue(driver.findElement(By.id("btn_one")).isDisplayed());
        // Clica no botão One.
        driver.findElement(By.id("btn_one")).click();


        // Verifica se o botão two está presente antes de ser pressionado.
        Assert.assertTrue(driver.findElement(By.id("btn_two")).isDisplayed());
        // Clica no botão Two.
        driver.findElement(By.id("btn_two")).click();


        /* O Botão Four ao ser clicado, muda de posição (Y). Fazendo com que o elemento não esteja presente na tela. */

        // Armazena a posição antes do elemento ser clicado.
        int x = driver.findElement(By.id("btn_link")).getLocation().getY();
        // Clica no botão four.
        driver.findElement(By.id("btn_link")).click();
        // Armazena a posição após o clique no botão.
        int xy = driver.findElement(By.id("btn_link")).getLocation().getY();


        /**Verificando a ausência dos botões após os cliques.**/

        //Botão One.
        Assert.assertTrue(!driver.findElement(By.id("btn_one")).isDisplayed());
        //Botão Two.
        Assert.assertTrue(!driver.findElement(By.id("btn_two")).isDisplayed());
        //Botão Four
        Assert.assertNotEquals(x, xy); // Verifica se as posições armazenadas são diferentes.

    }

    @Test
    public void testeIFrameButtons() {

        /** O selenium não consegue encontrar o elemento em determinadas resoluções, portanto a solução foi colocar um ScrollBy **/
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scrollBy(0,400)", "");

        driver.switchTo().frame(0); // mudando de frame.

        // Verifica se o botão one está presente antes de ser pressionado.
        Assert.assertTrue(driver.findElement(By.id("btn_one")).isDisplayed());
        // Clica no botão One.
        driver.findElement(By.id("btn_one")).click();

        // Verifica se o botão two está presente antes de ser pressionado.
        Assert.assertTrue(driver.findElement(By.id("btn_two")).isDisplayed());
        // Clica no botão Two.
        driver.findElement(By.id("btn_two")).click();

        /* O Botão Four ao ser clicado, muda de posição (Y). Fazendo com que o elemento não esteja presente na tela. */

        // Armazena a posição antes do elemento ser clicado.
        int x = driver.findElement(By.id("btn_link")).getLocation().getY();
        // Clica no botão four.
        driver.findElement(By.id("btn_link")).click();
        // Armazena a posição após o clique no botão.
        int xy = driver.findElement(By.id("btn_link")).getLocation().getY();


        /**Verificando a ausência dos botões após os cliques.**/
        //Botão One.
        Assert.assertTrue(!driver.findElement(By.id("btn_one")).isDisplayed());
        //Botão Two.
        Assert.assertTrue(!driver.findElement(By.id("btn_two")).isDisplayed());
        //Botão Four
        Assert.assertNotEquals(x, xy); // Verifica se as posições armazenadas são diferentes.


    }

    @Test
    public void testeCenarioFinal() {

        /* Executando cenário de testes. */
        driver.findElement(By.id("first_name")).sendKeys("Lucas Porto Gomes");
        driver.findElement(By.id("btn_one")).click();
        driver.findElement(By.id("opt_three")).click();

        WebElement element = driver.findElement(By.id("select_box"));
        Select combo = new Select(element);
        combo.selectByVisibleText("ExampleTwo");


        /* Validando cenário */
        Assert.assertEquals("Lucas Porto Gomes", driver.findElement(By.id("first_name")).getAttribute("value")); //Verifica se o campo foi preenchido corretamente.
        Assert.assertTrue(!driver.findElement(By.id("btn_one")).isDisplayed()); //Verifica se o botão foi clicado.
        Assert.assertTrue(driver.findElement(By.id("opt_three")).isSelected()); //Verifica se o botão está selecionado.
        Assert.assertEquals("ExampleTwo", combo.getFirstSelectedOption().getText()); // Verifica a opção selecionada.
        Assert.assertTrue(driver.findElement(By.className("img-responsive-center-block")).isDisplayed()); //verifica se o logo está presente.


    }

}
