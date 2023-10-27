async function createPostItem() {
  const postList = await fetchPosts();
  const postCtn = document.querySelector(".post-ctn");
  const cdn = "https://d26r7ovjsdxjo9.cloudfront.net/";

  postList.forEach((item) => {
    const postItem = document.createElement("div");
    postItem.classList.add("post-item");
    const img = document.createElement("img");
    const msg = document.createElement("p");
    img.src = cdn + item.img;
    msg.textContent = item.msg;
    postItem.appendChild(img);
    postItem.appendChild(msg);
    postCtn.appendChild(postItem);
  });
}

async function fetchPosts() {
  const response = await fetch("/api/posts");
  const postList = await response.json();
  console.log(`postList = ${postList}`);
  return postList;
}

createPostItem();
listenUploadFile();
async function listenUploadFile(){
  const form = document.querySelector('form');
  const button = document.querySelector('button');
  const msgInput = document.getElementById('msg');
  const fileInput = document.getElementById('file');
  const loadMsg = document.createElement("p");
  loadMsg.textContent = "正在上傳檔案...請耐心等待10秒";

  button.addEventListener('click', async function (event) {
    form.appendChild(loadMsg);
    event.preventDefault();
    const formData = new FormData();
    formData.append('msg', msgInput.value);
    formData.append('file', fileInput.files[0]);

    const response = await fetch('/api/upload', {
      method: 'POST',
      body: formData,
    })

    const resJson = await response.json();
    console.log(resJson);
    if(resJson.successful == true){
      window.alert("上傳成功");
      window.location = "/";
    }else{
      alert("上傳失敗");
    }
  });
}


