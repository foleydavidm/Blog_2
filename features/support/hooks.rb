require 'watir-webdriver'

Before do |scenario|
  @browser = Watir::Browser.new 'chrome'
end

After do |scenario|
  @browser.close
end