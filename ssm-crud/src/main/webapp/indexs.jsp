<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>员工列表</title>
    <%--
        web路径
        不以/开始的相对路径，找资源以当前路径为基准
        以/开头的相对路径，找资源以服务器路径为基准（http://localhost:8080）,需要加项目名

    --%>
    <!-- Bootstrap -->
    <script src="/static/js/jquery-3.2.1.js"></script>
    <link href="/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 员工修改的模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">EmpName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_static">email@example.com</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="email_update_input"
                                   placeholder="email@nwpu.edu.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_m_update_input" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_f_update_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">DeptName</label>
                        <div class="col-sm-5">
                            <%--部门提交ID--%>
                            <select class="form-control" name="dId" id="dept_update_select">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>


<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">EmpName</label>
                        <div class="col-sm-10">
                            <input type="email" name="empName" class="form-control" id="empName_add_input"
                                   placeholder="EmpName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="email_add_input"
                                   placeholder="email@nwpu.edu.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">Gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_m_add_input" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_f_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">DeptName</label>
                        <div class="col-sm-5">
                            <%--部门提交ID--%>
                            <select class="form-control" name="dId" id="dept_add_select">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<%--显示页面--%>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-lg-offset-8">
            <button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="emp_delete_all_btn">删除</button>
        </div>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all" />
                    </th>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>department</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%--分页文字信息--%>
        <div class="col-md-6" id="page_info_area"></div>
        <%--分页条信息--%>
        <div class="col-md-6" id="page_nav_area"></div>
    </div>
</div>
<script type="text/javascript">

    var totalRecord;
    var currentPage;

    /*页面加载完成后直接发送ajax请求*/
    $(function () {
        /*默认首页*/
        to_page(1)
    });

    function to_page(pn) {
        $.ajax({
            url: "/nemps",
            data: "pn=" + pn,
            type: "get",
            success: function (info) {
                console.log(info);
                /*解析显示数据*/
                build_emps_table(info);
                build_page_info(info);
                build_page_nav(info);
            }
        });
    }

    function build_emps_table(info) {
        $("#emps_table tbody").empty();

        var emps = info.context.pageInfo.list;
        $.each(emps, function (index, item) {
            /*alert(item.empName)*/
            var checkBoxTd = $("<td><input type='checkbox' class='check_item' /></td>");
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var genderTd = $("<td></td>").append(item.gender == "M" ? "男" : "女");
            var emailTd = $("<td></td>").append(item.email);
            var deptNameTd = $("<td></td>").append(item.department.deptName);

            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");

            editBtn.attr("edit-id", item.empId);

            var deleteBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");

            deleteBtn.attr("del-id", item.empId);

            var btnTd = $("<td></td>").append(editBtn).append(" ").append(deleteBtn);

            $("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd)
                            .append(genderTd).append(emailTd)
                            .append(deptNameTd).append(btnTd)
                            .appendTo("#emps_table tbody")

        });
    }

    /*显示分页信息*/
    function build_page_info(info) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前第 " + info.context.pageInfo.pageNum + " 页，" +
            "共 " + info.context.pageInfo.pages + " 页，" +
            "共 " + info.context.pageInfo.total + " 条记录");
        totalRecord = info.context.pageInfo.total;
        currentPage = info.context.pageInfo.pageNum;
    }

    /*显示导航条*/
    function build_page_nav(info) {

        $("#page_nav_area").empty();

        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if (info.context.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(info.context.pageInfo.pageNum - 1);
            });
        }

        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        if (info.context.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            nextPageLi.click(function () {
                to_page(info.context.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(info.context.pageInfo.pages);
            });
        }

        /*首页 + 前一页*/
        ul.append(firstPageLi).append(prePageLi);
        $.each(info.context.pageInfo.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if (info.context.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });

        /*下一页 + 末页*/
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

    /*重置表单*/
    function reset_form(elem) {
        $(elem)[0].reset();
        $(elem).find("*").removeClass("has-error has-success");
        $(elem).find(".help-block").text("");
    }
    
    /*点击新增按钮*/
    $("#emp_add_modal_btn").click(function () {
        reset_form("#empAddModal form"); /*清楚表单数据*/
        getDepts("#empAddModal select"); /*查出部门信息*/
        $("#empAddModal").modal({
            backdrop: "static"
        });
    });

    /*获取部门信息*/
    function getDepts(elem) {
        $(elem).empty();
        $.ajax({
            url: "/depts",
            type: "GET",
            success: function (info) {
                console.log(info);
                //显示部门信息
                $.each(info.context.depts, function () {
                    var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                    optionEle.appendTo(elem);
                });
            }
        })
    }
    
    /*校验*/
    function validate_add_form() {
        /*拿到数据，正则校验*/
        var empName = $("#empName_add_input").val();
        var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
        if (!regName.test(empName)) {
            //alert("用户名可以使2-5位汉字或6-16位英文数字组合");
            show_validate_info("#empName_add_input", "error", "用户名可以使2-5位汉字或6-16位英文数字组合");
            return false;
        } else {
            show_validate_info("#empName_add_input", "success", "")
        }
        var email = $("#email_add_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!regEmail.test(email)) {
            //alert("邮箱格式不正确");
            show_validate_info("#email_add_input", "error", "邮箱格式不正确");
            return false
        } else {
            show_validate_info("#email_add_input", "success", "");
        }
        return true;
    }

    /*显示校验提示信息*/
    function show_validate_info(elem, status, msg) {
        $(elem).parent().removeClass("has-success has-error"); /*清除之前的校验状态*/
        $(elem).next("span").text("");
        if (status == "success") {
            $(elem).parent().addClass("has-success");
            $(elem).next("span").text(msg)
        } else if (status == "error"){
            $(elem).parent().addClass("has-error");
            $(elem).next("span").text(msg);
        }
    }

    /*校验用户名是否可用*/
    $("#empName_add_input").change(function () {
        var empName = this.value; /*发送ajax请求校验用户名是否可用*/
        $.ajax({
            url:"/checkuser",
            data:"empName=" + empName,
            type:"POST",
            success:function (info) {
                if (info.code == 200) {
                    show_validate_info("#empName_add_input", "success", "用户名可用");
                    $("#emp_save_btn").attr("ajax_va", "success");
                } else {
                    show_validate_info("#empName_add_input", "error", info.context.va_msg);
                    $("#emp_save_btn").attr("ajax_va", "error");
                }
            }

        })

    })

    /*点击保存*/
    $("#emp_save_btn").click(function () {
        /*填写的表单数据提交给后端*/
        if (!validate_add_form()) { /*合法性校验*/
            return false;
        }
        if ($(this).attr("ajax_va") == "error") { /*判断用户名存在性校验*/
            return false;
        }
        /*alert($("#empAddModal form").serialize());*/
        $.ajax({
            url:"/emp",
            type:"POST",
            data:$("#empAddModal form").serialize(),
            success:function (info) {
                /*alert(info.msg);*/

                if (info.code == 200) {
                    $("#empAddModal").modal('hide');
                    to_page(totalRecord); /*来到最后一页显示刚才保存的数据，发送ajax请求显示最后一页数据*/
                } else {
                    console.log(info);
                    if (undefined != info.context.error_field.email) {
                        show_validate_info("#email_add_input", "error", info.context.error_field.email);
                    }
                    if (undefined != info.context.error_field.empName) {
                        show_validate_info("#empName_add_input", "error", info.context.error_field.empName);
                    }
                }
            }
        })
    });

    /*点击编辑按钮显示员工信息*/
    $(document).on("click", ".edit_btn", function () {
        /*alert("edit");*/
        /*查出部门信息显示部门列表*/
        getDepts("#empUpdateModal select");
        /*查出员工信息，显示详细信息*/
        getEmp($(this).attr("edit-id"));
        /*把员工id传递给模态框的更新按钮*/
        $("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));
        $("#empUpdateModal").modal({
            backdrop:"static"
        });
    })

    /*获取员工信息*/
    function getEmp(id) {
        $.ajax({
            url:"/emp/" + id,
            type:"GET",
            success:function (info) {
                console.log(info);
                var empData = info.context.emp;
                $("#empName_update_static").text(empData.empName);
                $("#email_update_input").val(empData.email);
                $("#empUpdateModal input[name=gender]").val([empData.gender]);
                $("#empUpdateModal select").val([empData.dId]);
            }
        });
    }

    /*点击更新*/
    $("#emp_update_btn").click(function () {
        /*验证邮箱合法性*/
        var email = $("#email_update_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!regEmail.test(email)) {
            //alert("邮箱格式不正确");
            show_validate_info("#email_update_input", "error", "邮箱格式不正确");
            return false
        } else {
            show_validate_info("#email_update_input", "success", "");
        }
        /*保存更新数据*/
        $.ajax({
            url:"/emp/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#empUpdateModal form").serialize(),
            success:function (info) {
                alert(info.msg);
                $("#empUpdateModal").modal("hide");
                to_page(currentPage);
            }
        });
        /*post方式提交*/
        /*$.ajax({
            url:"/emp/"+$(this).attr("edit-id"),
            type:"POST",
            data:$("#empUpdateModal form").serialize()+"&_method=PUT",
            success:function (info) {
                alert(info.msg);
            }
        });*/
    });


    $(document).on("click", ".delete_btn", function () {
        var empName = $(this).parents("tr").find("td:eq(2)").text();
        var empId = $(this).attr("del-id");
        if (confirm("确认删除[" + empName + "]吗?")) {
            $.ajax({
                url:"/emp/"+empId,
                type:"DELETE",
                success:function (info) {
                    alert(info.msg);
                    /*回到本页*/
                    to_page(currentPage);
                }
            })
        }
    });

    /*全选/全不选*/
    $("#check_all").click(function () {
        /*alert($(this).prop("checked"));*/
        $(".check_item").prop("checked", $(this).prop("checked"))
    });

    $(document).on("click", ".check_item", function () {
        var flag = $(".check_item:checked").length == $(".check_item").length;
        $("#check_all").prop("checked", flag);
    });

    /*点击全部删除*/
    $("#emp_delete_all_btn").click(function () {

        var empNames = "";
        var del_idstr = "";

        $.each($(".check_item:checked"), function () {
            empNames += $(this).parents("tr").find("td:eq(2)").text() + "|";
            del_idstr += $(this).parents("tr").find("td:eq(1)").text() + "-";
        });

        /*去除多余的逗号*/
        empNames = empNames.substring(0, empNames.length - 1);
        del_idstr = del_idstr.substring(0, del_idstr.length - 1);
        if (confirm("确认删除[" + empNames + "]吗?")) {
            $.ajax({
                url:"/emp/"+del_idstr,
                type:"DELETE",
                success:function (info) {
                    alert(info.msg);
                    to_page(currentPage);
                }
            });
        }
    });

</script>
</body>
</html>
