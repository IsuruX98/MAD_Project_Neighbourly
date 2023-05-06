package com.mad.neighbourlytest.models



class ItemDonationModel {

    var donationID : String? = null
    var typeDonation : String? = null
    var quantityDonation : String? = null
    var expDonation : String? = null
    var contactName : String? = null
    var contactNum : String?= null
    var dispatched : Boolean = false
    var uMail : String? = null
    var typeUser : String? = null


    constructor(donationID: String?,typeDonation : String?,quantityDonation : String? ,expDonation : String?,contactName : String?,contactNum : String?,dispatched : Boolean, uMail : String?,typeUser: String? ){
        this.donationID = donationID
        this.quantityDonation = quantityDonation
        this.typeDonation = typeDonation
        this.expDonation = expDonation
        this.contactName = contactName
        this.contactNum = contactNum
        this.dispatched = dispatched
        this.uMail = uMail
        this.typeUser = typeUser

    }
    constructor(){

    }


}


