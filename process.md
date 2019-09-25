# Create Quote
## pullIpmBoms
	查询最新的bom数据
	把ipnVO转换成WoodyIpn
	给bom结构加上最新的revisionNo
## generateQuote
### quoteDetail
#### 准备参数
    通过bom信息获得CompCategoryFillPrepareVO
    查询所有apn关联的WoodyCustomerApprovedCost
    通过regin查询所有的AdditionalCostMappingVO
    通过partNo查询所有的PartInfoVO
#### 包装quoteDetail
    cataId,cataGroup,CateItem,CateDesc,Manufacturer 通过上一步中的CompCategoryFillPrepareVO得到
    unitPrice通过PartInfoVO得到
    finalPrice通过WoodyCustomerApprovedCost得到
#### 执行公式
    查询WoodyAdditionalCostSettings,这些nonMaterial是所有需要计算的
    通过WoodyAdditionalCostSettings过滤NonMaterialCataId：过滤掉1070
    查询出所有Detail的公式，然后通过ipnType过滤
    找到detail适配的formula：通过detail的信息查到WoodyAdditionalCostSettings，
    然后WoodyAdditionalCostSettings.id = formula.itemId找到formula
    