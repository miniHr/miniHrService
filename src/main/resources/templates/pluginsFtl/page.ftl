<#macro page pageNo totalPage showPages callFunName>
    <div class="page_list clearfix">
        <#assign pageNum=(pageNo?number)>
        <#assign total=(totalPage?number)>
        <#if pageNum!=1>
        <a href="javascript:${callFunName+'('+1+')'};" class="top_page">首页</a>
        <a href="javascript:${callFunName+'('+(pageNum-1)+')'};" class="page_prev">上一页</a>
        </#if>
        <#if pageNum-showPages/2 gt 0>
            <#assign start = pageNum-(showPages-1)/2/>
            <#if showPages gt total>
                <#assign start = 1/>
            </#if>
        <#else>
            <#assign start = 1/>
        </#if>
        <#if total gt showPages>
            <#assign end = (start+showPages-1)/>
            <#if end gt total>
                <#assign start = total-showPages+1/>
                <#assign end = total/>
            </#if>
        <#else>
            <#assign end = total/>
        </#if>
        <#assign pages=start..end/>
        <#list pages as page>
            <#if page==pageNum>
                <a href="javascript:${callFunName+'('+page+')'};" class="current">${page}</a>
            <#else>
                <a href="javascript:${callFunName+'('+page+')'};">${page}</a>
            </#if>
        </#list>
        <#if pageNum!=total>
            <a href="javascript:${callFunName+'('+(pageNum+1)+')'};" class="page_next">下一页</a>
            <a href="javascript:${callFunName+'('+(total)+')'};" class="end_page">尾页</a>
        </#if>
    </div>
</#macro>