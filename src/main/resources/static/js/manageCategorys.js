function categoryAdd() {
    var name = $("#name").val();
    $.ajax({
        type:"get",
        url:"/manage/category/add",
        data:{
            "name":name
        },
        success:function(result) {
            window.location.reload();
        }
    });
}
function updateCategory(cid){
    var update = document.getElementById(cid);
    var iupdate = document.getElementById("i"+cid);
    var name = iupdate.value;
    if(update.innerHTML == "确定"){
        if(name == ""){
            alert("类别名称不能为空")
        }else{
            $.ajax({
                type:"get",
                url:"/manage/category/update",
                data:{
                    "id":cid,
                    "name":name
                },
                success:function(result) {
                    window.location.reload();
                }
            });
        }
    }

    var supdate = document.getElementById("s"+cid);
    update.innerHTML = "确定";
    iupdate.style.display="block";
    supdate.style.display="none";
}

function delCategory(id){
    if(confirm("确定删除该类别以及该类别下的所有文章吗？")) {
        $.ajax({
            type: "post",
            url: "/manage/category/delete",
            data: {
                "id" : id
            },
            success: function(result) {
                alert("删除成功");
                window.location.reload();
            }
        })
    }
}