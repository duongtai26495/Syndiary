	
	let app_name = "Syndiary";
	document.getElementById('logo_heading_desktop').innerHTML = app_name.toUpperCase();
	document.getElementById('logo_heading_mobile').innerHTML = app_name.toUpperCase();
	
	const add_new_mobile = document.querySelector(".add_new_mobile");
	const add_new = document.querySelector(".add_new");
	const diary_add = document.getElementById("diary_add");
	const close_new = document.getElementById("close_new");
	const menu_drawer = document.getElementById("menu_drawer");
	const close_menu = document.getElementById("close_menu");
	const openMenu = document.getElementById("toggle_menu");
	openMenu.addEventListener("click",()=>{
		menu_drawer.style.left = '0%';
	})
	close_menu.addEventListener("click",()=>{
		close_menu_drawer()
	})
	menu_drawer.addEventListener("click",()=>{
		close_menu_drawer()
	})
	
	const close_menu_drawer = () => menu_drawer.style.left = '-100%';

	const accordionContent = document.querySelectorAll(".accordion_content");
	accordionContent.forEach((item, index)=>{
		let arrow = item.querySelector(".accordion_arrow");
		let header = item.querySelector("header");
		let title = item.querySelector(".diary_title");
		header.addEventListener("click",()=>{
			content_toggle();
		})
		
		const content_toggle = () => {
			item.classList.toggle("open");
			let content = item.querySelector(".content");
			
			
			if(item.classList.contains("open")){
				content.style.height = `${content.scrollHeight}px`;
				content.style.marginTop = '10px';
				arrow.style.rotate = '180deg';
				title.style.maxHeight = `${title.scrollHeight}px`;
			}else{
				content.style.height = '0px';
				content.style.marginTop = '0px';
				arrow.style.rotate = '0deg';
				title.style.maxHeight = '30px';
			}
		}
	})
	
	add_new.addEventListener("click",()=>{
		diary_add.classList.toggle("open_new_diary");
	})
	add_new_mobile.addEventListener("click",()=>{
		diary_add.classList.toggle("open_new_diary");
	})
	close_new.addEventListener("click",()=>{
		diary_add.classList.remove("open_new_diary");
	})
	
	
function validateDiary (){
	let title = document.getElementById("title_new_diary").value;
	let content = document.getElementById("content_new_diary").value;
	let valid = document.getElementById("validation_diary");
	if(title == "" || content == ""){
		validation_diary.style.display = 'block';
		return false;	
	}else{
		validation_diary.style.display = 'none';
		return true;	
	}
}
	