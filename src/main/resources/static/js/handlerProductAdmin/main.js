// post product
let btnUpload = document.getElementById("btnUpload");
let inputImage = document.getElementById("inputImage");
let imagePreview = document.getElementById("imagePreview");
btnUpload.onclick = ()=>{
    inputImage.click();
    inputImage.onchange = ()=>{
        if (inputImage.files && inputImage.files[0]) {
            imagePreview.src = URL.createObjectURL(inputImage.files[0]);
        }
    }
}


//post product
let create = () =>{
    let formData = validateAndGetForm();
    if (!formData) return;
    postProduct('/api/v1/admin/product/create-product',formData);
}
let update = () =>{
    let formData = validateAndGetForm();
    console.log(formData);
    if (!formData) return;
    let id = document.getElementById("id").value;
    let url = `/api/v1/admin/product/update-product?id=${id}`;
    postProduct(url, formData);
}
let validateAndGetForm =()=>{
    let inputs = document.querySelectorAll(".input");
    let selects = document.querySelectorAll(".select");
    let name = document.getElementById("product-name");
    let price = document.getElementById("product-price");
    let quantityInStock = document.getElementById("quantity-in-stock");
    let category = document.getElementById("category");
    let brand = document.getElementById("brand");
    let image = document.getElementById("inputImage");
    let description = document.getElementById("description");

    let isValid = true;
    if(!description.value){
        description.style.border = '2px solid red';
        isValid = false;
    }
    description.onclick =()=> {
        description.style.border = '1px solid #ced4da';
    }

    inputs.forEach(item =>{
        if(!item.value || item.value < 0) {
            item.style.border = '2px solid red';
            isValid = false;
        }
        item.oninput =()=>{
            if(item.value || item.value >=0 ){
                item.style.border = '1px solid #ced4da';
            }
        }
        item.onblur =()=>{
            if(!item.value  || item.value < 0) item.style.border = '2px solid red'
            return false;
        }
    })
    selects.forEach(item=>{
        if(item.value === '0' ) {
            item.style.border = '2px solid red';
            isValid = false;
        }
        item.onchange =()=>{
            if(item.value !=='0' ){
                item.style.border = '1px solid #ced4da';
            }
            else{
                item.style.border = '2px solid red';
                return false;
            }
        }
    })
    if(!isValid) return false;
    let formData = new FormData();
    formData.append("name", name.value);
    formData.append("price", price.value);
    formData.append("quantityInStock", quantityInStock.value);
    formData.append("category", category.value);
    formData.append("brand", brand.value);
    formData.append("description", description.value);
    if(image.files[0]) {
        formData.append("image", image.files[0]);
    }
    return formData;
}
let postProduct =(url,formData) =>{
    fetch(url, {
        method: 'POST',
        body: formData,
    })
        .then(response => {
            if (!response.ok) {
                return response.json()
                    .then(errorData => {
                        throw new Error(errorData.message);
                    });
            }
            return response.json();
        })
        .then(data => {
            alert("Successfully")
        })
        .catch(error => {
            alert(error.message);
            window.location.reload();
        });
}
