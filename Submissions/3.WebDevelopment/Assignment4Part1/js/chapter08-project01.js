/* add loop and other code here ... in this simple exercise we are not
   going to concern ourselves with minimizing globals, etc */

for (i = 0; i < 3; i++)
{
    filename = filenames[i];
    title = titles[i];
    quantity =quantities[i];
    price = prices[i];
    total = calculateTotal(quantity, price);
    outputCartRow(filename, title, quantity, price, total);

}



/* using constant from ES2015 ... if using old browser switch to var */
const tax_rate = 0.10;
const shipping_threshold = 1000;

/* running total for subtotals */
