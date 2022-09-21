	
	let app_name = "Syndiary";
	document.getElementById('logo_heading_desktop').innerHTML = app_name.toUpperCase();
	document.getElementById('logo_heading_mobile').innerHTML = app_name.toUpperCase();
	
	const add_new = document.querySelector(".add_new");
	const diary_add = document.getElementById("diary_add");
	
	const menu_drawer = document.getElementById("menu_drawer");
	const close_menu = document.getElementById("close_menu");
	const openMenu = document.getElementById("toggle_menu");
	openMenu.addEventListener("click",()=>{
		menu_drawer.style.left = '0%';
	})
	close_menu.addEventListener("click",()=>{
		menu_drawer.style.left = '-100%';
	})
	
	

	const accordionContent = document.querySelectorAll(".accordion_content");
	accordionContent.forEach((item, index)=>{
		let header = item.querySelector("header");
		header.addEventListener("click",()=>{
			item.classList.toggle("open");
			
			let content = item.querySelector(".content");
			if(item.classList.contains("open")){
				content.style.height = `${content.scrollHeight}px`;
			}else{
				content.style.height = '0px';
			}
			console.log(content);
		})
	})
	
	add_new.addEventListener("click",()=>{
		diary_add.classList.toggle("open_new_diary");
	})
	
	