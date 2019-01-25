<#import "include/common-css.ftl" as css/>
<#import "include/common-js.ftl" as js/>
<!DOCTYPE html>
<html lang="en">
<head>
<@css.description/>
    <@css.ace/>
</head>

<body>
<#include "menu/top-menu.ftl"/>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
        <#include "menu/left-menu.ftl"/>
        </div>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>

                    <li>
                        <a href="#">Other Pages</a>
                    </li>
                    <li class="active">Blank Page</li>
                </ul><!-- .breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                         autocomplete="off"/>
									<i class="icon-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- #nav-search -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                    <#--<div class="row">-->
                        <div class="col-sm-4">
                            <div class="widget-box">
                                <div class="widget-header">
                                    <h4>Custom File Input</h4>

                                    <span class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                          <i class="icon-chevron-up"></i>
                                        </a>

                                        <a href="#" data-action="close">
                                          <i class="icon-remove"></i>
                                        </a>
                                    </span>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main">
                                        <input type="file" id="id-input-file-2"/>
                                        <input multiple="" type="file" id="id-input-file-3"/>
                                        <label>
                                            <input type="checkbox" name="file-format" id="id-file-format" class="ace"/>
                                            <span class="lbl"> Allow only images</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        <#--</div>-->
                            <button class="btn btn-primary">
                                <i class="icon-beaker align-top bigger-125"></i>
                                上传
                            </button>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->

    <#include "menu/right-menu.ftl"/>
    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->
<@js.ace/>
<#include "include/index-js.ftl">
<script>
    jQuery(function ($) {
        $('#id-input-file-1 , #id-input-file-2').ace_file_input({
            no_file:'No File ...',
            btn_choose:'Choose',
            btn_change:'Change',
            droppable:false,
            onchange:null,
            thumbnail:false //| true | large
            //whitelist:'gif|png|jpg|jpeg'
            //blacklist:'exe|php'
            //onchange:''
            //
        });
    })
</script>
</body>
</html>
