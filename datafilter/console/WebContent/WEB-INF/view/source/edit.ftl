<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>修改来源</title>
  <link href="/css/bootstrap.min14ed.css" rel="stylesheet">
  <link href="/css/animate.min.css" rel="stylesheet">
  <link href="/css/style.min862f.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="/css/filter-base.css"/>
  <style type="text/css">
    .source-sure,.dynamic-audit{
      margin-left: 10%;
    }
  </style>
</head>

<body class="gray-bg">
  <div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
      <div class="col-sm-12">
        <div class="ibox">
          <div class="ibox-title">
            <h5>
              <a href="sourceSettings.html">来源设置</a>
              >>
              <a href="javascript:;">修改来源</a>
            </h5>
          </div>
          <div class="ibox-content">
            <form id="form" action="" class="wizard-big">
              <input id="id" type="hidden" value="${source?if_exists.id?if_exists}">
              <fieldset>
                <div class="row">
                  <div class="col-sm-8 source-newCon">
                    <div class="form-group">
                      <label>来源名称&nbsp;&nbsp;&nbsp;：</label>
                      <input id="sourceName" name="sourceName" type="text" class="form-control required" value="${source?if_exists.sourceName?if_exists}"></div>
                    <div class="form-group">
                      <label>来源关键字：</label>
                      <input id="sourceKey" name="sourceKey" type="text" class="form-control required" value="${source?if_exists.sourceKey?if_exists}"></div>
                    <div class="form-group">
                      <label>审核规则&nbsp;&nbsp;&nbsp;：</label>
                      <select id="auditRule" class="source-examine">
                        <option value="1" <#if source?? && source.auditRule?? && source.auditRule == 1>selected=selected</#if>
                        >先发后审
                      </option>
                        <option value="2" <#if source?? && source.auditRule?? && source.auditRule == 2>selected=selected</#if>
                      >先审后发
                      </option>
                        <option value="3" <#if source?? && source.auditRule?? && source.auditRule == 3>selected=selected</#if>
                    >动态审核
                  </option>
                </select>

              </div>
            </div>
          </div>

        </fieldset>
      </form>
      <#if timesetList?? && timesetList?size gt 0 >
        <#list timesetList as timeset>
          <div class="dynamic-audit">
            <span class="after-trial">先审后发</span>
            <select id="startHour" class="startHour">
                <#list 0..23 as t>
                <option value="${t}" <#if timeset.startHour?? && timeset.startHour == t>selected=selected</#if>
                >${t}:00</option>
                </#list>
            </select>&nbsp;至&nbsp;
            <select id="endHour" class="endHour">
              <#list 1..24 as t>
                <option value="${t}" <#if timeset.endHour?? && timeset.endHour == t>selected=selected</#if>
                  >${t}:00
                </option>
              </#list>
            </select>
            <span class="add-release">+</span>
            <span class="delete-release">×</span>
          </div>
      </#list>
      </#if>
      <div class="source-sure">
        <button id="update-sure" class="btn btn-info btn-sm">确定</button>
        <button id="return" class="btn btn-info btn-sm">返回</button>
      </div>
    </div>

  </div>
</div>

</div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
  //改变审核规则下拉框
  $('#auditRule').change(function() {
    var auditRule = $(this).val();
    if (auditRule == 3) {
      if ($(".dynamic-audit") == undefined || $(".dynamic-audit").length < 1){
        $(".source-sure").before(addTimeSetStr());
      }
      $(".dynamic-audit").show();
    } else {
      $(".dynamic-audit").hide();
    }
  })
  //【确定】按钮
  $("#update-sure").click(function(){
    var id = $("#id").val();
    var sourceName = $("#sourceName").val();
    var sourceKey = $("#sourceKey").val();
    var auditRule = $("#auditRule").val();
    var startHour = [];
    var endHour = [];
    if(auditRule == 3) {
      $(".startHour").each(function(){
        startHour.push($(this).val());
      });
      $(".endHour").each(function(){
        endHour.push($(this).val());
      });
    }
    $.ajax({
        type:"get",
        url:"/source/update.do",
        data:"id=" + id + "&sourceName=" + sourceName + "&sourceKey=" + sourceKey + "&auditRule="
          + auditRule + "&startHour=" + startHour + "&endHour=" + endHour,
        dataType: "json",
        success:function(data){
            window.location.href = "/source/index.do";
        }
    });
  });
  //【返回】按钮
  $('#return').click(function(){
      window.location.href = "/source/index.do";
  })
})

//+新增
$(document).on("click",'.add-release',function(){
    var html = $(this).parents(".dynamic-audit").prop("outerHTML");
    $(this).parents(".dynamic-audit").after(html);
 });
//删除
$(document).on("click",'.delete-release',function(){
  $(this).parents(".dynamic-audit").remove();
});
function addTimeSetStr() {
  var s = "<div class=\"dynamic-audit\" >"
          + "<span class=\"after-trial\">先审后发</span>"
          + "<select id=\"startHour\" class=\"startHour\">";
      for (var i=0; i<=23; i++) {
        s += "<option value=" + i + ">" + i + ":00</option>";
      }
      s +=  "</select>&nbsp;至&nbsp;<select id=\"endHour\" class=\"endHour\">"
      for (var i=1; i<=24; i++) {
        s += "<option value=" + i + ">" + i + ":00</option>";
      }
      s +=  "</select>"
        +   "<span class=\"add-release\">+</span>"
        +   "<span class=\"delete-release\">×</span>"
        + "</div>";
  return s;
}
</script>