require('dotenv').config();
const puppeteer = require('puppeteer');
const delay = require('delay');
const { Connection, Request } = require("tedious");

let data;

// codigo do bot puppeteer
(async() => {
    const browser = await puppeteer.launch({
        headless: false,
    });

    console.log('Aguarde...');

    const page = await browser.newPage();
    await page.goto('https://www.awseducate.com/signin/SiteLogin');

    await page.type('[name="loginPage:siteLogin:loginComponent:loginForm:username"]', process.env.USERAWS);
    await page.type('[name="loginPage:siteLogin:loginComponent:loginForm:password"]', process.env.PASSAWS);

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
    data = await (await element.getProperty('textContent')).jsonValue();

    console.log(data)

    await browser.close();

    // // Create connection to database
    const config = {
        authentication: {
          options: {
            userName: "adminlocal", // update me
            password: "Safadinhos123@" // update me
          },
          type: "default"
        },
        server: "serverhumildificadores.database.windows.net", // update me
        options: {
          database: "bancohumildificadores", //update me
          encrypt: true
        }
      };
      
      const connection = new Connection(config);
      
      // Attempt to connect and execute queries if connection goes through
    await connection.on("connect", err => {
        if (err) {
          console.error(err.message);
        } else {
          queryDatabase();
        }
      });
      
    function queryDatabase() {
        console.log("Reading rows from the Table...");
        let momento = new Date()
        // Read all rows from table
        const request = new Request(
          `insert into biling values (${data}, '${momento.getFullYear()}-${momento.getMonth()+1}-${momento.getDate()}')`,
          (err, rowCount) => {
            if (err) {
              console.error(err.message);
            } else {
              console.log(`${rowCount} row(s) returned`);
            }
          }
        );
      
        request.on("row", columns => {
          columns.forEach(column => {
              console.log("------------")
            console.log("%s - %s", column.metadata.colName, column.value);
          });
        });
      
        connection.execSql(request);
      }
})();
