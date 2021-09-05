<#import "parts/common.ftl" as common>
<#import "parts/product.ftl" as product>
<#import "/spring.ftl" as spring/>

<@common.page>
    <a class="btn btn-info" href="/createDemandHistory">Update Demand History</a> <br/>

    Red means critical level and that the product ended and we should purchase this product<br/>
    Yellow means danger level and we are totally recommend to purchase this product<br/>
    Green means that the product demand is fine<br/>
    <div class="flex-container">
        <table class="table">
            <tr class="thead-dark ">
                <th scope="col">id</th>
                <th scope="col">Level</th>
                <th scope="col">Update date</th>
            </tr>
            <#list products as product>
                <tr>
                    <form class="form" method="POST" action="/changeUserInfo">
                        <td>
                            <a href="#">${product.product.id}</a>
                        </td>
                        <td bgcolor="
                            <#if product.level == "GREEN">
                                green
                            </#if>
                            <#if product.level == "YELLOW">
                                yellow
                            </#if>
                            <#if product.level == "RED">
                                red
                            </#if> "
                        >
                        </td>
                        <td>
                            <input type="text" class="form-control username" name="productLevel"
                                   value="${product.demandHistory.demandDate}"
                                   disabled>
                        </td>
                    </form>
                </tr>
            </#list>
        </table>
    </div>
</@common.page>