const React = require('react')
const ReactDOM = require('react-dom')

const AboutUsView = React.createClass({
  render: function(){
    return (
      <div className="about-us-page-container">
        <div className="auth-home-icon-container">
          <a href=" "><i className="fa fa-home fa-4x auth-home-icon" aria-hidden="true"></i></a>
        </div>
        <div className="multi-header text-center">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>

        <div className="about-us-container">
          <h1>About Us</h1>
          <p><strong><em>Presta Trading Post</em></strong> is the ultimate meet-up spot for those who have and those who don't.  Have you ever needed a specialty tool to fix something but you did not want to pay for an item that will sit around in your garage forever after you use it once?  Have you ever thought about wanting to try a stand-up paddle board to see if you like it or not and could not see having to pay $800+ just to find out? Well, here at Presta Trading Post we have people who have items just like these that are just sitting around in their garages and who are willing to rent or barter them out.  All you need to do is register today, and then you too can take advantage of these great deals.  Also as a registered member, you will be able to advertise all those items you have sitting around in your garage and begin to rent and barter them out.</p>
        </div>
      </div>
    )
  }
});

module.exports = AboutUsView
