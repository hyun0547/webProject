const hello = document.querySelector(".hello");

fetch(`https://api.adviceslip.com/advice`)
	.then((Response) => Response.json())
	.then((data) => hello.innerText = data.slip.advice);
