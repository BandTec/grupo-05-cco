const puppeteer = require('puppeteer');
const delay = require('delay');

(async() => {
    const browser = await puppeteer.launch({
        headless: true,
    });

    console.log('Aguarde...');

    const page = await browser.newPage();
    await page.goto('https://www.awseducate.com/signin/SiteLogin');

    await page.type('[name="loginPage:siteLogin:loginComponent:loginForm:username"]', 'jose.silva@bandtec.com.br');
    await page.type('[name="loginPage:siteLogin:loginComponent:loginForm:password"]', '@Joka0810');

    await page.click('.loginText');

    await page.waitForNavigation();

    await page.goto('https://www.awseducate.com/student/s/classrooms');

    await page.waitForTimeout(3000);

    await page.click('[data-id="a1v3m00000588gCAAQ"]');

    try {
        await page.waitForTimeout(2000);
        await page.click('.confirmy-modal-ok');
    } catch (error) {
        console.log(error)
    }

    await page.waitForTimeout(8000);
    console.log("Aguarde só mais um pouquinho.")

    page.goto('https://labs.vocareum.com/main/main.php?m=editor&nav=1&asnid=265109&stepid=265110');

    await page.waitForTimeout(10000);

    const element = await page.$("#awsstimetxtB");
    const data = await (await element.getProperty('textContent')).jsonValue();

    console.log(data)

    await browser.close();
})();